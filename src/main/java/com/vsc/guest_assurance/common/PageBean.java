package com.vsc.guest_assurance.common;

import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2020/10/9
 */
public class PageBean<T> {
    /**
     * 当前页
     */
    private Integer page = 1;
    /**
     * 每页显示的总条数
     */
    private Integer size = 10;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 是否有下一页
     */
    private Integer isMore;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 开始索引
     */
    private Integer startIndex;
    /**
     * 分页结果
     */
    private List<T> items;

    public PageBean() {
        super();
    }

    public PageBean(Integer page, Integer size, Long total) {
        super();
        this.page = page;
        this.size = size;
        this.total = total;
        String totalNumStr = String.valueOf(this.total);
        this.totalPage = (Integer.valueOf(totalNumStr)+this.size -1)/this.size;
        this.startIndex = (this.page-1)*this.size;
        this.isMore = this.page >= this.totalPage?0:1;
    }

    public PageBean(int page, int size, long total, List<T> items) {
        this.total = total;
        this.page = page;
        this.size = size;
        this.totalPage = size == 0 ? 1 : new Double(Math.ceil(1.0 * total / size)).intValue();
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
