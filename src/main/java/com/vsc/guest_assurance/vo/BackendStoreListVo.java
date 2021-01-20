package com.vsc.guest_assurance.vo;

import io.swagger.annotations.ApiModelProperty;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/14
 */
public class BackendStoreListVo {
    @ApiModelProperty("门店id")
    private Integer id;
    @ApiModelProperty("客户号")
    private String accountnumber;
    @ApiModelProperty("客户全称")
    private String name;
    //@ApiModelProperty("客户负责人")
    //private String principal;
    @ApiModelProperty("地址")
    private String address1_composite;
    @ApiModelProperty("城市")
    private String address1_City;
    @ApiModelProperty("所属部门")
    private String ecolabcn_department;
    @ApiModelProperty("累计点赞次数")
    private Integer thumbs_up_num;
    @ApiModelProperty("总分数")
    private Integer thumbs_up_points;
    @ApiModelProperty("综合评分")
    private String comprehensiveEvaluation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1_composite() {
        return address1_composite;
    }

    public void setAddress1_composite(String address1_composite) {
        this.address1_composite = address1_composite;
    }

    public String getAddress1_City() {
        return address1_City;
    }

    public void setAddress1_City(String address1_City) {
        this.address1_City = address1_City;
    }

    public String getEcolabcn_department() {
        return ecolabcn_department;
    }

    public void setEcolabcn_department(String ecolabcn_department) {
        this.ecolabcn_department = ecolabcn_department;
    }

    public Integer getThumbs_up_num() {
        return thumbs_up_num;
    }

    public void setThumbs_up_num(Integer thumbs_up_num) {
        this.thumbs_up_num = thumbs_up_num;
    }

    public Integer getThumbs_up_points() {
        return thumbs_up_points;
    }

    public void setThumbs_up_points(Integer thumbs_up_points) {
        this.thumbs_up_points = thumbs_up_points;
    }

    public String getComprehensiveEvaluation() {
        return comprehensiveEvaluation;
    }

    public void setComprehensiveEvaluation(String comprehensiveEvaluation) {
        this.comprehensiveEvaluation = comprehensiveEvaluation;
    }
}
