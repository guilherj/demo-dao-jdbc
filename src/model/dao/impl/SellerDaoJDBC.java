package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		

	}

	@Override
	public void update(Seller obj) {
		

	}

	@Override
	public void deleteById(Integer id) {
		

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// COMANDO SQL PARA BUSCA DE SELLER POR ID
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id); // <-- SETANDO O ID DA SENTENÇA SQL COMO O ID PASSADO POR ARGUMENTO NO MÉTODO
			rs = st.executeQuery();

			if (rs.next()) {

				Department dep = instantiateDepartment(rs); // USANDO O MÉTODO AUXILIAR DECLARADO ABAIXO
				Seller obj = instantiateSeller(rs, dep); // USANDO O MÉTODO AUXILIAR DECLARADO ABAIXO 
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			/*
			 * NÃO É PRECISO FECHAR A CONEXÃO COM O BANCO, POIS ESSE DAO PODE SER USADO
			 * PARA OUTRAS OPERAÇÕES, ENTÃO PODE DEIXAR PARA FECHAR A CONEXÃO NA CLASSE PROGRAM.
			 */
		}

	}

	/*
	 * OS MÉTODOS ABAIXO (instantiateSeller e instantiateDepartment) SERVEM COMO
	 * AUXILIARES PARA MELHORAR O CÓDIGO DO MÉTODO FindById, ASSIM ELE NÃO FICA
	 * MUITO GRANDE E A CLASSE FICA MAIS ORGANIZADA.
	 * 
	 * AS EXCESSÕES QUE PODEM DAR NESSES MÉTODOS ESTÃO SENDO PROPAGADAS PARA QUE SEJAM TRATADAS PELO CATCH
	 * QUANDO FOREM CHAMADOS NO MÉTODO FindById.
	 * 
	 * O CÓDIGO DESSES MÉTODOS ESTÃO INSTANCIANDO OS OBJETOS COM OS DADOS RECEBIDOS PELO
	 * RESULTSET "rs" É NECESSÁRIO FAZER ESSA OPERAÇÃO POIS ESTAMOS PROGRAMANDO NUMA
	 * LINGUAGEM ORIENTADA A OBJETO ENTÃO DEVE-SE INSTANCIAR OS OBJETOS COM SUAS
	 * ASSOCIAÇÕES NA MEMÓRIA.
	 */

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		
		return null;
	}

}
