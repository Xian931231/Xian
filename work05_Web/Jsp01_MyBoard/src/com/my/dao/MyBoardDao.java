package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;

public class MyBoardDao {
   //db랑 연결 , 수정 및 삭제 등 Dao에서 처리해준다.
   Connection con = null;
   
   public MyBoardDao() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("01.driver 연결");
      } catch (ClassNotFoundException e) {
         System.out.println("01.driver 연결실패");
         e.printStackTrace();
      }
   }
   
   //전체출력
   public List<MyBoardDto> selectAll(){
      
      try {
         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
         System.out.println("02.계정 연결");
      } catch (SQLException e) {
         System.out.println("02.계정 연결 실패");
         e.printStackTrace();
      }
      
      Statement stmt = null;
      ResultSet rs = null;
      String sql ="SELECT*FROM MYBOARD";
      List<MyBoardDto> res = new ArrayList<MyBoardDto>();
      
      try {
         stmt = con.createStatement();
         System.out.println("03.query 준비: "+sql);
         
         rs = stmt.executeQuery(sql);
         System.out.println("04.query 실행 및 리턴");
               //next() 다음에 정보가 있음 참, 없음 거짓
         while(rs.next()) {
            MyBoardDto dto = new MyBoardDto(rs.getInt(1),rs.getString(2),
                                     rs.getString(3),rs.getString(4),
                                     rs.getDate(5));
            res.add(dto); // res에 dto 값을 추가한다.
         }
         
         
      } catch (SQLException e) {
         System.out.println("3/4단계 에러");
         e.printStackTrace();
      } finally {
         try {
            rs.close();
            stmt.close();
            con.close();
            System.out.println("05. db종료\n");
         } catch (SQLException e) {
            System.out.println("05.db종료 에러");
            e.printStackTrace();
         }
      }
      
      return res;
   }
   
   //선택출력
   public MyBoardDto selectOne(int myno) {
      try {
    	  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
         System.out.println("02. 계정 연결");
      } catch (SQLException e) {
         System.out.println("02.계정 연결 실패");
         e.printStackTrace();
      }
      
      PreparedStatement pstm = null;
      ResultSet rs = null;
                                    //여기서 ?는 매개변수인 myno값이다
      String sql = "SELECT * FROM MYBOARD WHERE MYNO = ?";
      MyBoardDto res = null;
      
      try {
         pstm = con.prepareStatement(sql);
         pstm.setInt(1, myno);
         System.out.println("03. query 준비: sql");
         
         rs = pstm.executeQuery();
         System.out.println("04. query 실행 및 리턴");
            
         if(rs.next()) {
            res = new MyBoardDto();
            res.setMyno(rs.getInt(1));
            res.setMyname(rs.getString(2));
            res.setMytitle(rs.getString(3));
            res.setMycontent(rs.getString(4));
            res.setMydate(rs.getDate(5));
         }
         
      } catch (SQLException e) {
         System.out.println("3/4단계 오류");
         e.printStackTrace();
      }finally {
         try {
            rs.close();
            pstm.close();
            con.close();
            System.out.println("05.db 종료 \n");
         } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("05. db 종료 오류");
         }
      }
      
      
      return res;
   }
   
   //추가
   public int insert(MyBoardDto dto) {
	   
	  try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
		System.out.println("02. 계정연결");
	} catch (SQLException e) {
		System.out.println("02. 계정 연결 실패");
		e.printStackTrace();
	}
	
	PreparedStatement pstm = null;
	int res = 0;
	String sql = "INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL,?,?,?,SYSDATE)";  
	
	
	try {
		pstm = con.prepareStatement(sql);
		//?의 값 설정
		pstm.setString(1, dto.getMyname());
		pstm.setString(2, dto.getMytitle());
		pstm.setString(3, dto.getMycontent());
		System.out.println("03. query 준비: "+sql);
		
		res = pstm.executeUpdate();
		System.out.println("04.query 실행 및 리턴");
		
	} catch (SQLException e) {
		System.out.println("3/4단계오류");
		e.printStackTrace();
	}finally {
		try {
			pstm.close();
			con.close();
			System.out.println("05.db종료\n");
		} catch (SQLException e) {
			System.out.println("05.db종료 오류");
			e.printStackTrace();
		}
		
	}
	  
	  return res; 
   }
   
   //수정
   public int update(MyBoardDto dto) {
	   try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
		System.out.println("02.계정 연결");
	   } catch (SQLException e) {
		   System.out.println("02. 계정 연결 실패");
		   e.printStackTrace();
	   }			
	   
	   PreparedStatement pstm = null;
	   int res = 0;
	   String sql=" UPDATE MYBOARD SET MYTITLE=?, MYCONTENT=? WHERE MYNO=?";
	   
	   try {
		pstm = con.prepareStatement(sql);
		pstm.setString(1, dto.getMytitle());
		pstm.setString(2, dto.getMycontent());
		pstm.setInt(3, dto.getMyno());
		System.out.println("03. query 준비: "+sql);
		
		res=pstm.executeUpdate();
		System.out.println("04.query 실행 및 리턴");
		
	} catch (SQLException e) {
		System.out.println("3/4단계 오류");
		e.printStackTrace();
	}finally {
		try {
			pstm.close();
			con.close();
			System.out.println("05. db 종료\n");
		} catch (SQLException e) {
			System.out.println("05. db 종료 오류");
			e.printStackTrace();
		}
	}
	   return res;
   }
   
   //삭제 
   public int delete(int myno) {
	   try {
		   //db에 연결는 것을 con에 담아준다.
		con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","KH","KH");
		System.out.println("02. 계정 연결");
	} catch (SQLException e) {
		System.out.println("02. 계정 연결 실패");
		e.printStackTrace();
	}
	
	PreparedStatement pstm = null;
	int res = 0;
	String sql = "DELETE FROM MYBOARD WHERE MYNO=?";
	
	try {
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, myno);
		System.out.println("03. quert 준비: "+sql);
		
		res = pstm.executeUpdate();
		System.out.println("04.query 실행 및 리턴");
	} catch (SQLException e) {
		System.out.println("3/4 단계 오류");
		e.printStackTrace();
	}finally {
		try {
			pstm.close();
			con.close();
			System.out.println("05. db 종료\n");
		} catch (SQLException e) {
			System.out.println("05. db 종료 오류");
			e.printStackTrace();
		}
	}
	
	   
	   return res;
   }
   
   
   
   
}