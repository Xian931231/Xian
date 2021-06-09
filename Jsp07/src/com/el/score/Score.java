package com.el.score;
public class Score {
private String name;
private int kor;
private int eng;
private int math;
private int sum;
private double avg;
private String grade;
public Score() {}
public Score(String name, int kor, int eng, int math) {
super();
this.name = name;
this.kor = kor;
this.eng = eng;
this.math = math;
setSum();
setAvg();
setGrade();
}
public String getName() { return name; }
public int getKor() { return kor; }
public int getEng() { return eng; }
public int getMath() { return math; }
public int getSum() { return sum; }
public double getAvg() { return avg; }
public String getGrade() { return grade; }
public void setName(String name) { this.name = name; }
public void setKor(int kor) { this.kor = kor; }
public void setEng(int eng) { this.eng = eng; }
public void setMath(int math) { this.math = math; }
public void setSum() { this.sum = kor + eng + math; }
public void setAvg() { this.avg = (double)getSum() / 3; }
public void setGrade() {
switch((int)getAvg()/10) {
case 10:
case 9:
this.grade = "A";
break;
case 8:
this.grade = "B";
break;
case 7:
this.grade = "C";
break;
case 6:
this.grade = "D";
break;
default:
this.grade = "F";
break;
}
}
@Override
public String toString() {
return "Score [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
+ ", sum=" + sum + ", avg=" + avg + ", grade=" + grade + "]";
}
}