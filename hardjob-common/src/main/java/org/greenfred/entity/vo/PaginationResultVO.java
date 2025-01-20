package org.greenfred.entity.vo;



import java.util.ArrayList;
import java.util.List;

public class PaginationResultVO<T> {
    private Integer totalCount;
    private Integer pageSize;
    private Integer pageNo;
    private Integer pageTotal;
    private List<T> list = new ArrayList<T>();

    public PaginationResultVO(Integer totalCount, Integer pageSize, Integer pageNo, List<T> list) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PaginationResultVO(Integer totalCount, Integer pageSize, Integer pageNo, Integer pageTotal, List<T> list) {
        if (pageNo == 0) {
            pageNo = 1;
        }
        this.totalCount = totalCount;
        this.list = list;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public PaginationResultVO(List<T> list) {
        this.list = list;
    }

    public PaginationResultVO() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
