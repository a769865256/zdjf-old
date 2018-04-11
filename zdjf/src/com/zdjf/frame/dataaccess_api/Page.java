package com.zdjf.frame.dataaccess_api;

import java.io.Serializable;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页
 * 
 * @author liuxin 2011-9-10
 * @version 1.0 Page.java liuxin 2011-9-10
 */
public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1891808330846602745L;
	public static final long DEFAULT_LIMIT = 10L;
	public static final long DEFAULT_START = 0L;

	protected Long total = 0L;
	protected Long start = 0L;
	protected Long limit = DEFAULT_LIMIT;

	private Collection<?> dataList;

	public Page() {
	}

	public Page(Long start) {
		this(start, DEFAULT_LIMIT);
	}

	public Page(Long start, Long limit) {
		super();
		this.start = start;
		this.limit = limit;
	}

	/**
	 * 记录总数
	 * 
	 * @return
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * 开始的记录
	 * 
	 * @return
	 */
	public Long getStart() {
		return start;
	}

	/**
	 * 分页记录数
	 * 
	 * @return
	 */
	public Long getLimit() {
		return limit;
	}

	/**
	 * 结果集
	 * 
	 * @return
	 */
	public Collection<?> getDataList() {
		return dataList;
	}

	/**
	 * 当前的页
	 * 
	 * @return
	 */
	public Long getCurrentPage() {
		return (start / limit) + 1;
	}

	public void setCurrentPage(Long currentPage) {
		if (currentPage == 0) {
			currentPage = 1L;
		}
		this.start = (currentPage - 1) * this.limit;
	}

	/**
	 * 总页数
	 * 
	 * @return
	 */
	public Long getPageCount() {
		if (total == 0) {
			return 0L;
		}
		if (total % limit == 0) {
			return total / limit;
		}
		return (total / limit) + 1;
	}

	/**
	 * 下一页
	 * 
	 * @return 是否可以跳转到下一页
	 */
	public boolean nextPage() {
		if (this.total <= 0) {
			return false;
		}
		Long nextFirstResult = this.start + this.limit;
		if (nextFirstResult <= this.total) {
//			this.start = nextFirstResult;
			return true;
		}
		return false;
	}

	/**
	 * 上一页
	 * 
	 * @return 是否可以跳转到上一页
	 */
	public boolean prePage() {
		if (this.total <= 0) {
			return false;
		}
		Long nextFirstResult = this.start - this.limit;
		if (nextFirstResult >= 0) {
//			this.start = nextFirstResult;
			return true;
		}
		return false;

	}
	
	/**
	 * 需要展示到页面的内容
	 * 此方法只能用get方法
	 * @return
	 */
	public String showPage(HttpServletRequest request,
			String url){
		String queryString = request.getQueryString(); //获取当前请求参数部分
		String showQuery = "";   //原先的参数部分
		if(queryString==null || "".equals(queryString) || "null".equals(queryString)){
			
		}else{
			if(queryString!=null && queryString.indexOf("&")!=-1){				
				String query[] = queryString.split("&");
				for(int i=0;i<query.length;i++){
					String[] queryName = query[i].split("=");
					if(queryName[0].equals("page")){
						
					}else{
						if(queryName.length>1){
							showQuery += "&"+queryName[0]+"="+queryName[1];
						}
						
					}
				}
			}else if(queryString!=null && queryString.indexOf("=")!=-1){
				String[] queryName = queryString.split("=");
				if(queryName[0].equals("page")){
					
				}else{
					if(queryName.length>1){
						showQuery += "&"+queryName[0]+"="+queryName[1];
					}
					
				}
			}
		}
		long page = this.getCurrentPage(); //当前页
		long pageCount = this.getPageCount(); //尾页
		boolean prePage = this.prePage();  //是否有上一页
		boolean nextPage = this.nextPage(); //是否有下一页
		String firstStr = "<a href=\"javascript:\" class=\"no_allow\">首页</a>";  //
		String preStr = "<a href=\"javascript:\" class=\"no_allow\">上一页"+"</a>"; //
//		String preShowStr = "";  //上一页的数目
		String currentStr = "<span class=\"active\">"+ page +"</span>"; //当前页
		String nextShowStr = "";//下一页的显示数目
		String nextStr = "<a href=\"javascript:\" class=\"no_allow\">下一页"+"</a>";
		String lastStr = "<a href=\"javascript:\" class=\"no_allow\">尾页"+"</a>";
		if(prePage){  //首页、preStr可以加A标签的时候
			firstStr = "<a href="+request.getContextPath()+"/"+url+"?page=1"+showQuery+">首页</a>";
			preStr = "<a href="+request.getContextPath()+"/"+url+"?page="+(page-1)+showQuery+">上一页</a>";
		}
		if(nextPage){
			nextShowStr = "<a href="+request.getContextPath()+"/"+url+"?page="+(page+1)+showQuery+">"+(page+1)+"</a>";
			nextStr = "<a href="+request.getContextPath()+"/"+url+"?page="+(page+1)+showQuery+">下一页</a>";
			lastStr = "<a href="+request.getContextPath()+"/"+url+"?page="+pageCount+showQuery+">尾页</a>";
		}
		String showPage = "<div class=\"cap_page\">";
		showPage += firstStr + preStr + currentStr + nextShowStr + nextStr + lastStr;
		showPage += "</div>";
		return showPage;
	}

	/**
	 * 跳转页数
	 * 
	 * @param number
	 *            要跳转的页数
	 * 
	 * @return 是否可以跳转到指定的页
	 */
	public boolean gotoPage(Long number) {
		return false;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public void setDataList(Collection<?> dataList) {
		this.dataList = dataList;
	}

}
