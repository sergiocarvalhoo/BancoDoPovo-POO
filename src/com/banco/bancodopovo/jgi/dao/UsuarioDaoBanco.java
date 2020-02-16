package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.UsuarioDao;
import com.banco.bancodopovo.jgi.validations.Validations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class UsuarioDaoBanco implements UsuarioDao {


    private ConFactory connection;

    public UsuarioDaoBanco(){
       connection = new ConFactory();
    }

    @Override
    public boolean insertUsuario(Usuario usuario){

        String sql = "insert into cliente (cpf,nome,email,cidade,estado,nascimento,tipoconta,senha) values ("
                + "'" + usuario.getCpf() + "'," + "'" + usuario.getNome() + "'," + "'" + usuario.getEmail()
                + "'," + "'" + usuario.getCidade() + "'," + "'" + usuario.getEstado() + "'," +
                "'" + usuario.getNascimento() + "',"
                + "'" + usuario.getTipoConta() + "'," + "'" + usuario.getSenha() + "')";

        int connectionResult = connection.executeSQL(sql,true);
        if(connectionResult > 0){
            return true;
        }
        return false;


    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean deleteUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public Usuario getUsuarioBy(String data,String bySearch) {

        ResultSet result = connection.getQueryResult("SELECT * from cliente WHERE " + bySearch + "= '" + data + "'",false);

        int countRow = 0;
        String cpf = "", nome = "",email = "",cidade = "",estado = "",nascimento = "",tipoconta = "",senha = "", cc = "", cp = "";

        try{
            while(result.next()){

                countRow ++;

                cpf = result.getString("cpf");
                nome = result.getString("nome");
                email = result.getString("email");
                cidade = result.getString("cidade");
                estado = result.getString("estado");
                nascimento = result.getString("nascimento");
                tipoconta = result.getString("tipoconta");
                senha = result.getString("senha");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if(countRow == 1){
            if(tipoconta == "mista"){
                cc = "corrente";
                cp = "poupança";
            }
            return (new Usuario(nome,cpf,email,nascimento,estado,Validations.validarCidade(cidade),
                    Validations.validarTipoConta(cc,cp),senha));
        }
        return null;
    }
}
