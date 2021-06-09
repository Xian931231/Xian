package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MyMemberDto;

import static common.JDBCTemplate.*;

public class MyMemberDao {
	/*
	 * ������ ���(ADMIN)
	 *  1.ȸ�� ��ü ����
	 *  2.���Ե� ȸ��(MYENABLED='Y')�� ����
	 *  3.ȸ�� ��� ����
	 */
	
	//ȸ����ü ��ȸ
	public List<MyMemberDto> selectAll(){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyMemberDto> res = new ArrayList<MyMemberDto>();
		
		String sql ="SELECT * FROM MYMEMBER ORDER BY MYNO DESC";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query �غ� : " +sql);
			
			rs= pstm.executeQuery();
			System.out.println("04. query ���� �� ����");
			
			while(rs.next()){
				MyMemberDto tmp = new MyMemberDto();
				tmp.setMyno(rs.getInt(1));
				tmp.setMyid(rs.getString(2));
				tmp.setMypw(rs.getString(3));
				tmp.setMyname(rs.getString(4));
				tmp.setMyaddr(rs.getString(5));
				tmp.setMyphone(rs.getString(6));
				tmp.setMyemail(rs.getString(7));
				tmp.setMyenabled(rs.getString(8));
				tmp.setMyrole(rs.getString(9));
				
				res.add(tmp);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05.db ���� \n");
		}
		
		return res;
	}
	
	//���Ե� ȸ�� Ȯ��
	public List<MyMemberDto> selectEnabled(){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyMemberDto> res = new ArrayList<MyMemberDto>();
		
		String sql ="SELECT * FROM MYMEMBER WHERE MYENABLED='Y' ORDER BY MYNO DESC";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query �غ� : " +sql);
			
			rs= pstm.executeQuery();
			System.out.println("04. query ���� �� ����");
			
			while(rs.next()){
				MyMemberDto tmp = new MyMemberDto();
				tmp.setMyno(rs.getInt(1));
				tmp.setMyid(rs.getString(2));
				tmp.setMypw(rs.getString(3));
				tmp.setMyname(rs.getString(4));
				tmp.setMyaddr(rs.getString(5));
				tmp.setMyphone(rs.getString(6));
				tmp.setMyemail(rs.getString(7));
				tmp.setMyenabled(rs.getString(8));
				tmp.setMyrole(rs.getString(9));
				
				res.add(tmp);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05.db ���� \n");
		}
		
		return res;
	}
	
	//���� ��ȸ
	public MyMemberDto selectUser(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyMemberDto res = null;
		
		String sql = " SELECT * FROM MYMEMBER WHERE MYNO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03. query �غ�: " +sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query ���� �� ����");
			
			if(rs.next()) {
				res = new MyMemberDto();
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getNString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
				
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db ���� \n");
		}
		
		return res;
	}
	
	//ȸ����� ����
	public int updateRole(int myno, String myrole) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE MYMEMBER SET MYROLE=? WHERE MYNO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myrole);
			pstm.setInt(2, myno);
			System.out.println("03. query �غ�: "+sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query ���� �� ����");
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db ����\n");
		}
		
		
		
		return res;
	}
	
	
	
	
	
	
	/*
	 * ����� ���(USER)
	 * 1.�α���
	 * 2.ȸ������
	 * 3.�� ���� ��ȸ
	 * 4.�� ���� ����
	 * 5.ȸ�� Ż��
	 */
	public MyMemberDto login(String id, String pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs= null;
		MyMemberDto res = new MyMemberDto();
		
		String sql = "SELECT * FROM MYMEMBER WHERE MYID=? AND MYPW=? AND MYENABLED=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, "Y");
			System.out.println("03. query �غ�: "+sql);
			
			rs = pstm.executeQuery();
			
			System.out.println("04. query ���� �� ����");
			
			while(rs.next()){
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db ����\n");
		}
		
		
		return res;
	}
	
	//�� ���� ����
	public boolean updateUser(MyMemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql=" UPDATE MYMEMBER SET MYADDR=?, MYPHONE=?, MYEMAIL=? WHERE MYNO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyaddr());
			pstm.setString(2, dto.getMyphone());
			pstm.setString(3, dto.getMyemail());
			pstm.setInt(4, dto.getMyno());
			System.out.println("03. query �غ�: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query ���� �� ����");		
			
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db����\n");
		}
		//return���� ���� ������ ��밡��
		return (res>0)?true:false;
	}
	
	
	//ȸ�� Ż��
	public boolean deleteUser(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql ="UPDATE MYMEMBER SET MYENABLED = 'N' WHERE MYNO=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03. query �غ�:" +sql);
			
			res = pstm.executeUpdate();
			
			System.out.println("04 query ���� �� ����");
			
			if(res>0) {
				commit(con);
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db ����\n");
		}
		
		
		
		return (res>0)?true:false;
	}
	
	//���̵� �ߺ�üũ
	public String idChk(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		String sql = "SELECT * FROM MYMEMBER WHERE MYID=?" ;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			System.out.println("03. query �غ�: "+sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query ���� �� ����");
			
			while(rs.next()) {
				res = rs.getString(2);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db ����\n");
		}
		
		return res;
	}
	//ȸ������
	public int insertUser(MyMemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO MYMEMBER VALUES(MYNOSEQ.NEXTVAL,?,?,?,?,?,?,'Y','USER') ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			System.out.println("03. query �غ�:"+sql);
			
			res = pstm.executeUpdate();
			System.out.println("04.query ���� �� ����");
			
			if(res>0) {
				commit(con);
			}
					
		
		} catch (SQLException e) {
			System.out.println("3/4 �ܰ� ����");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05.db����\n");
		}
		
		return res;
	}
	
}
