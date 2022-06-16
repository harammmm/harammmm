public class Student {

	protected int Average=0;
	
	public Student(int scoreA, int scoreB, int scoreC) {
		this.Average = ((scoreA+scoreB+scoreC)/3);   //score a, b, c 값을 이용하여 평균 값 구하는 생성자 생성
	}
	
	public int getAverage() {
		return Average;
	}
	
	public static void main(String[]args) {
		int scoreA =10;
		int scoreB= 20;
		int scoreC =30;
		Student s = new Student(scoreA, scoreB, scoreC);
		
		System.out.println("평균 점수는 " + s.getAverage()+ " 입니다.");
	}
}