package com.zwl.javaBean;

import java.util.List;

/**
 * ��װ��ҳ���߶���
 * ���ڷ�װÿҳ�������Լ����ݵ�һЩ��Ϣ
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/14 20:17
 */
public class PageBean<T> {
    private int group;//����
    private int totalCount;//�ܼ�¼��
    private int totalPage;//��ҳ��
    private List<T> list;//ÿҳ�����ݼ���
    private int currentPage;//��ǰ��ҳ��
    private int rows;//ÿҳ�ļ�¼��

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
