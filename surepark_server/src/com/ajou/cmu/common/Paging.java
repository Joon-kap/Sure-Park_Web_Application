package com.ajou.cmu.common;


public class Paging {
	private int currentPage = 1; 	// ?��?��?��?���?
	private int totalCount;	 		// ?���? 게시�? ?��
	private int totalPage;	 		// ?���? ?��?���? ?��
	private int blockCount = 10;	// ?�� ?��?���??��  게시물의 ?��
	private int blockPage = 5;	 	// ?�� ?��면에 보여�? ?��?���? ?��
	private int startCount;	 		// ?�� ?��?���??��?�� 보여�? 게시�??�� ?��?�� 번호
	private int endCount;	 		// ?�� ?��?���??��?�� 보여�? 게시�??�� ?�� 번호
	private int startPage;	 		// ?��?�� ?��?���?
	private int endPage;	 		// 마�?�? ?��?���?
	private int lastCount;			
	private String url;
	private StringBuffer pagingHtml;
	
	public Paging(int currentPage, int totalCount, String url){
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.url = url;
		
		// ?���? ?��?���? ?��
		totalPage = (int) Math.ceil((double) totalCount / blockCount);
		if (totalPage == 0) {
			totalPage = 1;
		}
		// ?��?�� ?��?���?�? ?���? ?��?���? ?��보다 ?���? ?���? ?��?���? ?���? ?��?��
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		// ?��?�� ?��?���??�� 처음�? 마�?�? �??�� 번호 �??��?���?.
		startCount = (currentPage - 1) * blockCount;
		endCount = startCount + blockCount - 1;
		// ?��?�� ?��?���??? 마�?�? ?��?���? �? 구하�?.
		startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;
		// 마�?�? ?��?���?�? ?���? ?��?���? ?��보다 ?���? ?���? ?��?���? ?���? ?��?��
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		//
		
		lastCount = totalCount;
		if (endCount < totalCount) lastCount = endCount + 1;
		
		// ?��?�� block ?��?���?
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
