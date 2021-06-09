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
   //db�� ���� , ���� �� ���� �� Dao���� ó�����ش�.
   Connection con = null;
   
   public MyBoardDao() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("01.driver ����");
      } catch (ClassNotFoundException e) {
         System.out.println("01.driver �������");
         e.printStackTrace();
      }
   }
   
   //��ü���
   public List<MyBoardDto> selectAll(){
      
      try {
         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
         System.out.println("02.���� ����");
      } catch (SQLException e) {
         System.out.println("02.���� ���� ����");
         e.printStackTrace();
      }
      
      Statement stmt = null;
      ResultSet rs = null;
      String sql ="SELECT*FROM MYBOARD";
      List<MyBoardDto> res = new ArrayList<MyBoardDto>();
      
      try {
         stmt = con.createStatement();
         System.out.println("03.query �غ�: "+sql);
         
         rs = stmt.executeQuery(sql);
         System.out.println("04.query ���� �� ����");
               //next() ������ ������ ���� ��, ���� ����
         while(rs.next()) {
            MyBoardDto dto = new MyBoardDto(rs.getInt(1),rs.getString(2),
                                     rs.getString(3),rs.getString(4),
                                     rs.getDate(5));
            res.add(dto); // res�� dto ���� �߰��Ѵ�.
         }
         
         
      } catch (SQLException e) {
         System.out.println("3/4�ܰ� ����");
         e.printStackTrace();
      } finally {
         try {
            rs.close();
            stmt.close();
            con.close();
            System.out.println("05. db����\n");
         } catch (SQLException e) {
            System.out.println("05.db���� ����");
            e.printStackTrace();
         }
      }
      
      return res;
   }
   
   //�������
   public MyBoardDto selectOne(int myno) {
      try {
    	  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
         System.out.println("02. ���� ����");
      } catch (SQLException e) {
         System.out.println("02.���� ���� ����");
         e.printStackTrace();
      }
      
      PreparedStatement pstm = null;
      ResultSet rs = null;
                                    //���⼭ ?�� �Ű������� myno���̴�
      String sql = "SELECT * FROM MYBOARD WHERE MYNO = ?";
      MyBoardDto res = null;
      
      try {
         pstm = con.prepareStatement(sql);
         pstm.setInt(1, myno);
         System.out.println("03. query �غ�: sql");
         
         rs = pstm.executeQuery();
         System.out.println("04. query ���� �� ����");
            
         if(rs.next()) {
            res = new MyBoardDto();
            res.setMyno(rs.getInt(1));
            res.setMyname(rs.getString(2));
            res.setMytitle(rs.getString(3));
            res.setMycontent(rs.getString(4));
            res.setMydate(rs.getDate(5));
         }
         
      } catch (SQLException e) {
         System.out.println("3/4�ܰ� ����");
         e.printStackTrace();
      }finally {
         try {
            rs.close();
            pstm.close();
            con.close();
            System.out.println("05.db ���� \n");
         } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("05. db ���� ����");
         }
      }
      
      
      return res;
   }
   
   //�߰�
   public int insert(MyBoardDto dto) {
	   
	  try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
		System.out.println("02. ��������");
	} catch (SQLException e) {
		System.out.println("02. ���� ���� ����");
		e.printStackTrace();
	}
	
	PreparedStatement pstm = null;
	int res = 0;
	String sql = "INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL,?,?,?,SYSDATE)";  
	
	
	try {
		pstm = con.prepareStatement(sql);
		//?�� �� ����
		pstm.setString(1, dto.getMyname());
		pstm.setString(2, dto.getMytitle());
		pstm.setString(3, dto.getMycontent());
		System.out.println("03. query �غ�: "+sql);
		
		res = pstm.executeUpdate();
		System.out.println("04.query ���� �� ����");
		
	} catch (SQLException e) {
		System.out.println("3/4�ܰ����");
		e.printStackTrace();
	}finally {
		try {
			pstm.close();
			con.close();
			System.out.println("05.db����\n");
		} catch (SQLException e) {
			System.out.println("05.db���� ����");
			e.printStackTrace();
		}
		
	}
	  
	  return res; 
   }
   
   //����
   public int update(MyBoardDto dto) {
	   try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
		System.out.println("02.���� ����");
	   } catch (SQLException e) {
		   System.out.println("02. ���� ���� ����");
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
		System.out.println("03. query �غ�: "+sql);
		
		res=pstm.executeUpdate();
		System.out.println("04.query ���� �� ����");
		
	} catch (SQLException e) {
		System.out.println("3/4�ܰ� ����");
		e.printStackTrace();
	}finally {
		try {
			pstm.close();
			con.close();
			System.out.println("05. db ����\n");
		} catch (SQLException e) {
			System.out.println("05. db ���� ����");
			e.printStackTrace();
		}
	}
	   return res;
   }
   
   //���� 
   public int delete(int myno) {
	   try {
		   //db�� ����� ���� con�� ����ش�.
		con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","KH","KH");
		System.out.println("02. ���� ����");
	} catch (SQLException e) {
		System.out.println("02. ���� ���� ����");
		e.printStackTrace();
	}
	
	PreparedStatement pstm = null;
	int res = 0;
	String sql = "DELETE FROM MYBOARD WHERE MYNO=?";
	
	try {
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, myno);
		System.out.println("03. quert �غ�: "+sql);
		
		res = pstm.executeUpdate();
		System.out.println("04.query ���� �� ����");
	} catch (SQLException e) {
		System.out.println("3/4 �ܰ� ����");
		e.printStackTrace();
	}finally {
		try {
			pstm.close();
			con.close();
			System.out.println("05. db ����\n");
		} catch (SQLException e) {
			System.out.println("05. db ���� ����");
			e.printStackTrace();
		}
	}
	
	   
	   return res;
   }
   
   
   
   
}