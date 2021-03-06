package com.ajou.cmu.common;


public class Paging {
	private int currentPage = 1; 	// ??¬??΄μ§?
	private int totalCount;	 		// ? μ²? κ²μλ¬? ?
	private int totalPage;	 		// ? μ²? ??΄μ§? ?
	private int blockCount = 10;	// ? ??΄μ§??  κ²μλ¬Όμ ?
	private int blockPage = 5;	 	// ? ?λ©΄μ λ³΄μ¬μ€? ??΄μ§? ?
	private int startCount;	 		// ? ??΄μ§??? λ³΄μ¬μ€? κ²μκΈ?? ?? λ²νΈ
	private int endCount;	 		// ? ??΄μ§??? λ³΄μ¬μ€? κ²μκΈ?? ? λ²νΈ
	private int startPage;	 		// ?? ??΄μ§?
	private int endPage;	 		// λ§μ?λ§? ??΄μ§?
	private int lastCount;			
	private String url;
	private StringBuffer pagingHtml;
	
	public Paging(int currentPage, int totalCount, String url){
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.url = url;
		
		// ? μ²? ??΄μ§? ?
		totalPage = (int) Math.ceil((double) totalCount / blockCount);
		if (totalPage == 0) {
			totalPage = 1;
		}
		// ??¬ ??΄μ§?κ°? ? μ²? ??΄μ§? ?λ³΄λ€ ?¬λ©? ? μ²? ??΄μ§? ?λ‘? ?€? 
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		// ??¬ ??΄μ§?? μ²μκ³? λ§μ?λ§? κΈ?? λ²νΈ κ°?? Έ?€κΈ?.
		startCount = (currentPage - 1) * blockCount;
		endCount = startCount + blockCount - 1;
		// ?? ??΄μ§??? λ§μ?λ§? ??΄μ§? κ°? κ΅¬νκΈ?.
		startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;
		// λ§μ?λ§? ??΄μ§?κ°? ? μ²? ??΄μ§? ?λ³΄λ€ ?¬λ©? ? μ²? ??΄μ§? ?λ‘? ?€? 
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		//
		
		lastCount = totalCount;
		if (endCount < totalCount) lastCount = endCount + 1;
		
		// ?΄?  block ??΄μ§?
		pagingHtml = new StringBuffer();
			pagingHtml.append("<a href='javascript:"+url+"(1)'>");
			pagingHtml.append("<img src='../../images/web/car/btn_qq.gif' class='valign_btm' />");
			pagingHtml.append("</a>");
			
			pagingHtml.append("<a href='#' onclick='"+url+"(");
			if(currentPage > blockPage){
				pagingHtml.append(+(startPage - 1));
			}
			else{
				pagingHtml.append(startPage);
			}
			pagingHtml.append(")'>");
			pagingHtml.append("<img src='../../images/web/car/btn_q.gif' hspace='5' class='valign_btm' />");
			pagingHtml.append("</a>");
			
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {
				pagingHtml.append("<font class='page_list1'>");
				pagingHtml.append(i);
				pagingHtml.append("</font>");
			} else {
				pagingHtml.append("<a href='javascript:"+url+"("+i+")'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}
			pagingHtml.append("&nbsp;");
		}

			pagingHtml.append("<a href='javascript:"+url+"("+(endPage + 1)+")'>");
			pagingHtml.append("<img src='../../images/web/car/btn_p.gif' hspace='5' class='valign_btm' />");
			pagingHtml.append("</a>");
			pagingHtml.append("<a href='javascript:"+url+"("+totalCount+")'>");
			pagingHtml.append("<img src='../../images/web/car/btn_pp.gif' hspace='5' class='valign_btm' />");
			pagingHtml.append("</a>");		
			
		}
	//}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public int getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}
	public int getBlockPage() {
		return blockPage;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getStartCount() {
		return startCount;
	}
	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}
	public int getEndCount() {
		return endCount;
	}
	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}
	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public int getLastCount() {
		return lastCount;
	}

	public void setLastCount(int lastCount) {
		this.lastCount = lastCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
