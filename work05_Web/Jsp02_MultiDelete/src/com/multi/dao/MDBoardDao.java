package com.multi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.multi.dto.MDBoardDto;

import common.JDBCTemplate;
						//JDBCTemplate를 상속받아준다.
public class MDBoardDao extends JDBCTemplate {

	//게시판 목록
	public List<MDBoardDto> selectAll(){
		Connection con = getConnection();
		Statement stmt = null; //쿼리문 실행문을 담기위함
		ResultSet rs = null; //쿼리문 결과값 가져오기위함
		List<MDBoardDto> res = new ArrayList<MDBoardDto>();
		
		String sql = "SELECT*FROM MDBOARD ORDER BY SEQ DESC";
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비: "+sql);
			
			rs= stmt.executeQuery(sql); //쿼리문 실행한 결과값을 rs에 저장한다.
			System.out.println("04. query 실행 및 리턴");
			while(rs.next()) {
				MDBoardDto tmp = new MDBoardDto(rs.getInt(1),rs.getString(2),rs.getString(3),
												rs.getString(4),rs.getDate(5));
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("05.db 종료\n");
		}
		
		return res;
	}
	
	//글 선택
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs= null;
		
		String sql = "SELECT * FROM MDBOARD WHERE SEQ = ?";
		MDBoardDto res = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03. query 준비 : "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			if(rs.next()) {
				res = new MDBoardDto();
				res.setSeq(rs.getInt(1));
				res.setWriter(rs.getString(2));
				res.setTitle(rs.getString(3));
				res.setContent(rs.getString(4));
				res.setRegdate(rs.getDate(5));
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
				System.out.println("05.db 종료 오류");
			}
		}
		
		
		
		
		
		return res;
	}
	
	//글 작성
	public int insert(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql=" INSERT INTO MDBOARD VALUES(MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("03.query 준비: "+sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05.db 종료\n");
		}
		
		
		
		return res;
	}
	
	//글 수정
	public int update(MDBoardDto dto) {
		Connection con = getConnection();
		//쿼리문 실행을 위해 준비
		PreparedStatement pstm = null;
		//실행된 값을 res에 담기위해 준비
		int res = 0;
		
		String sql=" UPDATE MDBOARD SET TITLE=?, CONTENT=? WHERE SEQ=? ";
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("03.query 준비: "+sql);
			
			res= pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res>0) {
				//저장
				commit(con);
			}
			
			} catch (SQLException e) {
			System.out.println("3/4단계 오류");	
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05.db 종료\n");
		}
		
		
		
		return res;
	}
	
	//글 삭제
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "DELETE FROM MDBOARD WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03. query 준비: "+sql);
			
			res= pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
			
			
			System.out.println("04. query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("05. 3/4단계 오류");
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				con.close();
				System.out.println("05. db종료\n");
			} catch (SQLException e) {
				System.out.println("05. db 종료 오류");
				e.printStackTrace();
			}
		}

		
		return res;
	}
	
	//다중 삭제
	public int multiDelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		
		String sql ="DELETE FROM MDBOARD WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i=0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				
				//addBatch() : 메모리를 적재해줌
				pstm.addBatch();
				System.out.println("03. query 준비: "+sql+"(삭제할 번호: "+seq[i]+")");
			}
				
			
			//int[]로 리턴
			//executeBatch() : 적재된 메모리를 한번에 실행시켜줌
			cnt = pstm.executeBatch();
			System.out.println("04.query 실행 및 리턴");
			
			
			//성공: -2
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]==-2) {
					res++;
				}
			}
			
			if(seq.length == res) {
				commit(con);
			}else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		
		
		
		return res;
	}
	
	
	
	
	
}
