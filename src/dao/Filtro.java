/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private static final String SQL_INSERT = "INSERT INTO productos (codigo,nombre,cantidad,precio,tipo,disponibilidad) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET  nombre = ?, tipo=?, cantidad=?, precio=?, disponibilidad = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE codigo=?";
    private static final String SQL_READ = "SLECT * FROM productos WHERE codigo = ?";
    private static final String SQL_READALL = "SELECT * FROM productos";
    
    Conexion con = new Conexion();
    
    
    public boolean create(Producto g) {
        
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCodigo());
            ps.setString(2, g.getNombre());
            ps.setInt(3, g.getCantidad());
            ps.setDouble(4, g.getPrecio());
            ps.setString(5, g.getTipo());
            ps.setBoolean(6, true);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE, null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1,key.toString());
            if(ps.executeUpdate() >0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    
    public boolean update(Producto c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getId());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(6, c.getCodigo());
            ps.setString(1,c.getNombre());
            ps.setString(2, c.getTipo());
            ps.setInt(3, c.getCantidad());
            ps.setDouble(4, c.getPrecio());
            ps.setBoolean(5, c.getDisponibilidad());
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    
    public Producto read(String key) {
        Producto f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps =con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key);
            
            rs = ps.executeQuery();
            while(rs.next()){
                f = new Producto (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getBoolean(7));
            }
            rs.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    
    public ArrayList<Producto> readAll() {
        ArrayList<Producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Producto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getBoolean(7)));
            }
            rs.close();
        }catch(SQLException ex){
            Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE,null,ex);
        }
        return all;
    }
    
    
    
}
