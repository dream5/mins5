package com.mins5.share.common.service;

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

}