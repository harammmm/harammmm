package jspstudy.domain;

public class PageMaker {
	
	private int totalCount; //��ü ������ ����
	private int startPage; //ù��° ��ȣ
	private int endPage; //��������° ��ȣ
	private boolean prev; //���� ��ư
	private boolean next; // ���� ��ư
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
	
	
	
	
	//���������� �������� ���� ���� ��ư ���� �����ϴ� �޼ҵ�
	public void calcData() {
		
		endPage = (int)(Math.ceil(scri.getPage()/(double)displayPageNum) * displayPageNum);
		//System.out.println(endPage);
		
		startPage = (endPage-displayPageNum)+1;
		//System.out.println(startPage);
		
		int tempEndPage = (int)(Math.ceil(totalCount/(double)scri.getPerPageNum()));
		//tempEndPage -> ���� ������ ����
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage; 
		}
	
		prev = startPage ==1 ? false:true;
		next = endPage*scri.getPerPageNum()>=totalCount ? false:true;
		//System.out.println(next);
	
}
}
