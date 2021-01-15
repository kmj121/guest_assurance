package com.vsc.guest_assurance.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/14
 */
public class BackendStoreListVo {
    @ApiModelProperty("客户号")
    private String customerNum;
    @ApiModelProperty("客户全称")
    private String customerFullName;
    @ApiModelProperty("客户负责人")
    private String principal;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("城市")
    private String cityName;
    @ApiModelProperty("所属部门")
    private String departmentName;
    @ApiModelProperty("累计点赞次数")
    private String thumbUpNumber;
    @ApiModelProperty("综合评分")
    private String comprehensiveEvaluation;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getThumbUpNumber() {
        return thumbUpNumber;
    }

    public void setThumbUpNumber(String thumbUpNumber) {
        this.thumbUpNumber = thumbUpNumber;
    }

    public String getComprehensiveEvaluation() {
        return comprehensiveEvaluation;
    }

    public void setComprehensiveEvaluation(String comprehensiveEvaluation) {
        this.comprehensiveEvaluation = comprehensiveEvaluation;
    }
}
