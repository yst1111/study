package com.yst.entity.domain;

import com.yst.entity.pojo.Student;
import io.swagger.annotations.ApiModel;

import java.util.UUID;

/**
 * @creator: ly-yangst
 * @date: 2022/4/26
 */
@ApiModel(value = "公司入参",description = "公司入参")
public class CompanyDTO {
    private String id;

    private String companyNo;//公司编码

    private String profession;//行业

    private String addr;//地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


    public static class CompanyBuilder{
        private String profession;//专业
        private String companyNo;//公司编码
        private String addr;//地址

        public static CompanyBuilder create(){
            return new CompanyBuilder();
        }

        public CompanyBuilder profession(String profession){
            this.profession = profession;
            return this;
        }
        public CompanyBuilder companyNo(String companyNo){
            this.companyNo = companyNo;
            return this;
        }
        public CompanyBuilder addr(String addr){
            this.addr = addr;
            return this;
        }
        public CompanyDTO build(){
            CompanyDTO company = new CompanyDTO();
            company.setId(UUID.randomUUID().toString().replace("-",""));
            company.setProfession(this.profession);
            company.setCompanyNo(this.companyNo);
            company.setAddr(this.addr);
            return company;
        }

    }



}
