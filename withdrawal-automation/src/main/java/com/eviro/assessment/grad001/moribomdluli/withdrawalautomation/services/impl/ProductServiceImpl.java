package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services.impl;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.dto.ProductDto;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.Contact;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.Investor;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.Product;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.ProductE;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.exception.ProductException;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.repositories.ProductRepo;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    private static final double INITIAL_BAL = 1000.0;

    @Override
    public ProductDto addNewProduct(ProductDto dto) {

        if(dto==null)return null;

        dto.setBalance(INITIAL_BAL);
        Product p = dtoToProduct(dto);

        return toProductDto(productRepo.save(p));
    }

    @Override
    public List<ProductDto> getAllProductsByInvestorId(long investorId) {

        if(investorId<1)return null;

        return productRepo.findAllByInvestor_Id(investorId).stream().map(this::toProductDto).toList();
    }

    @Override
    public void createWithdrawalNotice(HttpServletResponse response,long prodId,double requestedAmt) throws ProductException {

        Product product  = productRepo.findById(prodId).get();

        if(product.getInvestmentType()== ProductE.RETIREMENT && product.getInvestor().getAge()<66){
            throw new ProductException("You can't process withdrawal digitally at your age");
        }

        if(requestedAmt>product.getBalance()*0.9){
            throw new ProductException("You can't process withdrawal of more than 90% of the current balance");
        }
        String html = generateWithdrawalNoticeHTML(product,requestedAmt);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"example.pdf\"");

        try {
            HtmlConverter.convertToPdf(html, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private ProductDto toProductDto(Product product){
        ProductDto p =new ProductDto();

        p.setProductId(product.getId());
        p.setInvestorId(product.getInvestor().getId());
        p.setName(product.getName());
        p.setInvestmentType(product.getInvestmentType());
        p.setBalance(product.getBalance());

        return p;
    }
   private Product dtoToProduct(ProductDto dto){
       Investor v = new Investor();
       v.setId(dto.getInvestorId());

       Product p = new Product();
       p.setId(dto.getProductId());
       p.setInvestor(v);
       p.setName(dto.getName());
       p.setInvestmentType(dto.getInvestmentType());
       p.setBalance(dto.getBalance());

       return p;
    }

    private String generateWithdrawalNoticeHTML(Product prod,double requestedAmt){

        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Document</title>
                    <style>
                        h1{
                            text-align: center;
                        }
                        span{
                            border-bottom: 1px solid black;
                        }
                        div{
                            margin-left: 50px;
                        }
                    </style>
                                
                </head>
                <body>
                    <h1>Withdrawal Notice#</h1>
                    <div>
                        <h3>Investor Information</h3>
                        <p>
                """);
        Investor v = prod.getInvestor();
        Contact c = v.getContacts();
        DecimalFormat df =new DecimalFormat("#,##0.00");
        html.append("<strong>Name: </strong><span>+")
                .append(v.getFirstname())
                .append(" ")
                .append(v.getLastname())
                .append("</span></p><p>")
                .append("<strong>Contact Cell No.: </strong><span>")
                .append(c.getCelNumber()==null?"N/A":c.getCelNumber())
                .append("</span> <strong> Home: </strong><span>")
                .append(c.getHomeTel()==null?"N/A":c.getHomeTel())
                .append("</span><strong> Email: </strong><span>")
                .append(c.getEmail()==null? "N/A":c.getEmail())
                .append("</span></p></div><div><h3>Product Information</h3><p><strong>Product Id: </strong> <span>#")
                .append(prod.getId())
                .append("</span></p><p><strong>Product Name: </strong> <span>")
                .append(prod.getName()==null? "N/A":prod.getName())
                .append("</span></p><p><strong>Product Type: </strong> <span>")
                .append(prod.getInvestmentType().name())
                .append("</span></p><p><strong>Withdrawal Amount: R</strong> <span>")
                .append(df.format(requestedAmt))
                .append("</span></p></div></body></html>");

        return html.toString();
    }
}

