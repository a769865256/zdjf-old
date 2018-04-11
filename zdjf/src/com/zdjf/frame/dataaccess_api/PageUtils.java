package com.zdjf.frame.dataaccess_api;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



public class PageUtils {

	private static final Logger LOG = Logger.getLogger(PageUtils.class);

	private static Long DEFAULT_LIMIT;
	

	/** 总页数 */
    private static int totalPage = 0;
    /** 导航页码数 */
    private static int navigatePages = 8;
    /** 所有导航页号 */
    private static Long[] navigatePageNumbers = null;
    /** 当前页码 */
    private static Long currPage = 0l;
    
	static {
		Properties prop = new Properties();
		Resource resource = new ClassPathResource("project.properties");
		InputStream in = null;
		try {
			in = resource.getInputStream();
			prop.load(in);
			DEFAULT_LIMIT = Long.parseLong(prop.getProperty("page.default_limit").trim());
			if (LOG.isInfoEnabled()) {
//				LOG.info("加载默认分页数" + DEFAULT_LIMIT);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 创建Page工具栏 <br/>
	 * 返回Map中包含<br/>
	 * pageStart : 分页开始页码 <br/>
	 * pageEnd : 分页结束页码
	 * 
	 * @param page
	 * @return
	 */
	public static Map<String, Object> createPagePar(Page page) {
		Map<String, Object> result = new HashMap<String, Object>();
		long pageStart = 1;
		if (page.getCurrentPage() > 6L) {
			pageStart = page.getCurrentPage() - 5L;
		}
		long pageEnd = pageStart + 10L;
		if (pageEnd > page.getPageCount()) {
			pageEnd = page.getPageCount();
		}
		currPage=page.getCurrentPage();
		calcNavigatePageNumbers(page);
		result.put("pageStart", pageStart);
		result.put("pageEnd", pageEnd);
		result.put("prePage", page.prePage());
		result.put("nextPage", page.nextPage());
		result.put("currentPage", currPage);
		result.put("NavigatePageNumbers",navigatePageNumbers );
		return result;
	}
	public static Map<String, Object> createPagePar(String pre,Page page) {
		Map<String, Object> result = new HashMap<String, Object>();
		long pageStart = 1;
		if (page.getCurrentPage() > 6L) {
			pageStart = page.getCurrentPage() - 5L;
		}
		long pageEnd = pageStart + 10L;
		if (pageEnd > page.getPageCount()) {
			pageEnd = page.getPageCount();
		}
		currPage=page.getCurrentPage();
		calcNavigatePageNumbers(page);
		result.put(pre+"pageStart", pageStart);
		result.put(pre+"pageEnd", pageEnd);
		result.put(pre+"prePage", page.prePage());
		result.put(pre+"nextPage", page.nextPage());
		result.put(pre+"currentPage", currPage);
		result.put(pre+"NavigatePageNumbers",navigatePageNumbers );
		return result;
	}
	 /**
     * Description: 计算导航页<br>
	 * @param page 
     */
    private static void calcNavigatePageNumbers(Page page) {
        // 当总页数小于或等于导航页码数时
    	totalPage=page.getPageCount().intValue();
        if (totalPage <= navigatePages) {
            navigatePageNumbers = new Long[totalPage];
            for (int i = 0; i < totalPage; i++ ) {
                navigatePageNumbers[i] = (long) (i + 1);
            }
        } else { // 当总页数大于导航页码数时
            navigatePageNumbers = new Long[navigatePages];
            Long startNum = currPage - navigatePages / 2;
            Long endNum = currPage + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1l;
                // (最前navPageCount页
                for (int i = 0; i < navigatePages; i++ ) {
                    navigatePageNumbers[i] = startNum++ ;
                }
            } else if (endNum > totalPage) {
                endNum = (long) totalPage;
                // 最后navPageCount页
                for (int i = navigatePages - 1; i >= 0; i-- ) {
                    navigatePageNumbers[i] = endNum-- ;
                }
            } else {
                // 所有中间页
                for (int i = 0; i < navigatePages; i++ ) {
                    navigatePageNumbers[i] = startNum++ ;
                }
            }
        }
    }

	public static Page createPage(String prefix, HttpServletRequest request) {
		FastPage page = new FastPage();
		if (prefix == null) {
			prefix = "";
		} else {
		}
		Long rowCount = pasLong(request.getParameter(prefix + "rowCount"));
		Long start = pasLong(request.getParameter(prefix + "start"));
		Long total = pasLong(request.getParameter(prefix + "total"));
		Long limit = pasLong(request.getParameter(prefix + "limit"));
		Long currentPage = pasLong(request.getParameter(prefix + "currentPage"));
		Long nextPage = pasLong(request.getParameter(prefix + "nextPage"));
		Object orderByPropertyStart = pasObject(request.getParameter(prefix + "orderByPropertyStart"));
		Object orderByPropertyEnd = pasObject(request.getParameter(prefix + "orderByPropertyEnd"));
		if (limit == null || limit == 0) {
			limit = DEFAULT_LIMIT;
		}
		if (rowCount != null) {
			page.setTotal(rowCount);
		}
		if (start != null) {
			page.setStart(start);
		}
		if (limit != null) {
			page.setLimit(limit);
		}
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		if (nextPage != null) {
			page.setNextPage(nextPage);
		}
		if (orderByPropertyStart != null) {
			page.setOrderByPropertyStart(orderByPropertyStart);
		}
		if (orderByPropertyEnd != null) {
			page.setOrderByPropertyEnd(orderByPropertyEnd);
		}
		if (total != null) {
			page.setTotal(total);
		}
		return page;
	}

	private static Object pasObject(String s) {
		if (NumberUtils.isNumber(s)) {
			return pasLong(s);
		}
		return s;
	}

	private static Long pasLong(String s) {
		if (s == null || "".equals(s)) {
			return null;
		}
		return Long.parseLong(s);
	}

	/**
	 * 获取分页
	 * 
	 * @return 分页对象
	 */
	public static Page createPage(HttpServletRequest request) {
		return createPage(null, request);
	}
	public static int getTotalPage() {
		return totalPage;
	}
	public static void setTotalPage(int totalPage) {
		PageUtils.totalPage = totalPage;
	}
	public static int getNavigatePages() {
		return navigatePages;
	}
	public static void setNavigatePages(int navigatePages) {
		PageUtils.navigatePages = navigatePages;
	}
	public static Long[] getNavigatePageNumbers() {
		return navigatePageNumbers;
	}
	public static void setNavigatePageNumbers(Long[] navigatePageNumbers) {
		PageUtils.navigatePageNumbers = navigatePageNumbers;
	}
	public static Long getCurrPage() {
		return currPage;
	}
	public static void setCurrPage(Long currPage) {
		PageUtils.currPage = currPage;
	}
	

}
