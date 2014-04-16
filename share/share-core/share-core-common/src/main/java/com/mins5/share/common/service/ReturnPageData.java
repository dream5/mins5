package com.mins5.share.common.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoutian
 * @since 2014-2-28
 * @param <T>
 */
public class ReturnPageData<T> extends ReturnData<T> {

	private final int currentPage;
	private final int onePageSize;
	private long totalResults;

	public ReturnPageData(int currentPage, int onePageSize) {
		this.currentPage = Math.max(1, currentPage);
		this.onePageSize = Math.max(1, onePageSize);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getOnePageSize() {
		return onePageSize;
	}

	public int getTotalPages() {
		return totalResults == 0 ? 0 : (int) Math.ceil((double) totalResults / (double) this.onePageSize);
	}
	
	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public int getStartRow() {
		return (currentPage - 1) * onePageSize;
	}

	public boolean isFirstPage() {
		return !hasPreviousPage();
	}

	public boolean isLastPage() {
		return !hasNextPage();
	}

	public boolean hasPreviousPage() {
		return this.currentPage > 1;
	}

	public boolean hasNextPage() {
		return (this.currentPage * this.onePageSize) < this.totalResults;
	}
	
	
	/***以下为扩展方法 ,供前台分页标签使用 ,add by zhanglin  20140416****/

	/**
     * 当前页之前和之后显示的页数个数 如：假设当前页是 6 共有11页 那么 显示分页条会显示 1 2 3 4 5 [6] 7 8 9 10 11.
     */
    private int num = 5; 
    
	/**  
     * 获取前一页  
     * @return  
     */  
    public int getPrev(){   
        return currentPage-1;   
    }   
       
    /**  
     * 获取后一页  
     * @return  
     */  
    public int getNext(){   
        return currentPage+1;   
    }   
    
	
    /**
     * 当前页之前页的集合
     * @return
     */
    public List<Integer> getPrevPages() {   
        List<Integer> list = new ArrayList<Integer>();   
        int _frontStart = 1;   
           
        if (currentPage > num) {   
            _frontStart = currentPage - num;   
        }   
        for (int i=_frontStart; i<currentPage; i++) {   
            list.add(i);   
        }   
        return list;   
    }   
    
    /**  
     * 当前页的后num条页 假设当前页是 6 共有11页 如：7 8 9 10 11  
     * @return  后两页的集合
     */  
    public List<Integer> getNextPages() {   
    	 List<Integer> list = new ArrayList<Integer>();   
         int _endCount = num;   
         if (getTotalPages()<=num)
         {
         	_endCount = getTotalPages();
         }
         else if (num < getTotalPages() && (currentPage+num)<getTotalPages()) {   
             _endCount = currentPage+_endCount;   
         }    
            
         for (int i=currentPage+1;i<=_endCount; i++) {   
             list.add(i);   
         }   
         return list;
    }   
    
    
	
}