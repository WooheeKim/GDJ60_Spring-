package com.woo.s1.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woo.s1.util.DBConnection;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.woo.s1.product.ProductDAO.";
	
	
	// delete
	public int setProductDelete(Long productNum) throws Exception {
		
		return sqlSession.delete(NAMESPACE+"setProductDelete", productNum);
	}
	
	public Long getProductNum() throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+"getProductNum");
		
	}
	
	
	public List<ProductOptionDTO> getProductOptionList() throws Exception {
		List<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCTOPTION";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setOptionNum(rs.getLong("OPTIONNUM"));
			productOptionDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productOptionDTO.setOptionName(rs.getString("OPTIONNAME"));
			productOptionDTO.setOptionPrice(rs.getLong("OPTIONPRICE"));
			productOptionDTO.setOptionStock(rs.getLong("OPTIONSTOCK"));
			ar.add(productOptionDTO);
		}
		
		DBConnection.disConnect(rs, st, con);
		
		return ar;
		
	}
	public int setAddProductOption(ProductOptionDTO productOptionDTO) throws Exception {
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCTOPTION (OPTIONNUM, PRODUCTNUM, OPTIONNAME, OPTIONPRICE, OPTIONSTOCK) "
				+ "VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		st.setLong(1, productOptionDTO.getProductNum());
		st.setString(2, productOptionDTO.getOptionName());
		st.setLong(3, productOptionDTO.getOptionPrice());
		st.setLong(4, productOptionDTO.getOptionStock());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnect(st, connection);
		
		return result;
	}
	
	public ProductDTO getProductDetail(ProductDTO productDTO) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+"getProductDetail", productDTO);		
	}
	
	public List<ProductDTO> getProductList() throws Exception{
	
		return sqlSession.selectList(NAMESPACE+"getProductList");
	}
		
	public int setProductAdd(ProductDTO productDTO) throws Exception {
		
		return sqlSession.insert(NAMESPACE+"setProductAdd", productDTO);		
	}
	
}