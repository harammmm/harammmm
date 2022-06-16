package jspstudy.domain;

public class PageMaker {
	
	private int totalCount; //전체 데이터 갯수
	private int startPage; //첫번째 번호
	private int endPage; //마지막번째 번호
	private boolean prev; //이전 버튼
	private boolean next; // 다음 버튼
	private int displayPageNum = 10;
	private SearchCriteria scri;
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
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
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public SearchCriteria getScri() {
		return scri;
	}
	public void setScri(SearchCriteria scri) {
		this.scri = scri;
	}
	
	
	
	
	//시작페이지 끝페이지 이전 다음 버튼 값을 생성하는 메소드
	public void calcData() {
		
		endPage = (int)(Math.ceil(scri.getPage()/(double)displayPageNum) * displayPageNum);
		//System.out.println(endPage);
		
		startPage = (endPage-displayPageNum)+1;
		//System.out.println(startPage);
		
		int tempEndPage = (int)(Math.ceil(totalCount/(double)scri.getPerPageNum()));
		//tempEndPage -> 실제 페이지 갯수
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage; 
		}
	
		prev = startPage ==1 ? false:true;
		next = endPage*scri.getPerPageNum()>=totalCount ? false:true;
		//System.out.println(next);
	
}
}
