/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import dao.Filtro;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

/**
 *
 * @author LN710Q
 */
public final class Consulta extends JFrame{
     public JLabel lblnumProducto, lblNombre, lblCodigo, lbltipo, lblCantidad, lblExistencia;
    
    public JTextField numProducto, nombre, cantidad, codigo,precio;
    public JComboBox tipo;
    
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC =130, ALTOC=30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblnumProducto);
        container.add(lblNombre);
        container.add(lblCodigo);
        container.add(lbltipo);
        container.add(lblCantidad);
        container.add(lblExistencia);
        container.add(numProducto);
        container.add(nombre);
        container.add(cantidad);
        container.add(codigo);
        container.add(precio);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize (600,600);
        eventos();
        
    }
    public final void agregarLabels(){
        lblnumProducto = new JLabel("id Producto");
        lblNombre = new JLabel("Nombre:");
        lblCodigo = new JLabel("Codigo");
        lblCantidad = new JLabel("Cantidad");
        lbltipo = new JLabel("Tipo");
        lblExistencia = new JLabel("Disponibilidad");
        //Arreglar coordenadas
        lblnumProducto.setBounds(1240, 10, ANCHOC, ALTOC);
        
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblCantidad.setBounds(10, 60, ANCHOC, ALTOC);
        lbltipo.setBounds(1250, 60, ANCHOC, ALTOC);
        lblExistencia.setBounds(10, 140, ANCHOC, ALTOC);
    }
    
    public final void formulario(){
        numProducto = new JTextField();
        codigo = new JTextField();
        precio = new JTextField();
        cantidad = new JTextField();
        tipo = new JComboBox();
        nombre = new JTextField();
        si = new JRadioButton("si",true);
        no = new JRadioButton("no", false);
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();
        tipo.addItem("Fruta");
        tipo.addItem("Bebida");
        tipo.addItem("Dulce");
        tipo.addItem("Verdura");
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        codigo.setBounds(140,10,ANCHOC,ALTOC);
        nombre.setBounds(140,60,ANCHOC,ALTOC);
        numProducto.setBounds(1240,100,ANCHOC,ALTOC);
        precio.setBounds(140,130,ANCHOC,ALTOC);
        cantidad.setBounds(140,160,ANCHOC,ALTOC);
        si.setBounds(140,140,50,ALTOC);
        no.setBounds(210,140,50,ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla(){
        tm = new DefaultTableModel(){
            @Override
            public Class <?> getColumnClass(int column){
                switch (column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Cantidad");
        tm.addColumn("Precio");
        tm.addColumn("Tipo");
        tm.addColumn("Disponibilidad");
        
        Filtro fd = new Filtro();
        ArrayList<Producto> productos = fd.readAll();
        
        for(Producto pro: productos){
            tm.addRow(new Object[]{pro.getCodigo(),pro.getNombre(),pro.getCantidad(),pro.getPrecio(), pro.getTipo(),pro.getDisponibilidad()});
        }
        resultados.setModel(tm);
    }
    
    public void eventos(){
        insertar.addActionListener((ActionEvent e) -> {
            Filtro fd = new Filtro();
            Producto f = new Producto(nombre.getText(),codigo.getText(),"Fruta",Integer.parseInt(cantidad.getText()),Double.parseDouble(precio.getText()),true);
            
            if(no.isSelected()){
                f.setDisponibilidad(false);
            }
            if(fd.create(f)){
                JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
            }
        });
        actualizar.addActionListener((ActionEvent e) -> {
            Filtro fd = new Filtro();
            Producto f = new Producto(nombre.getText(),codigo.getText(),"Fruta",Integer.parseInt(cantidad.getText()),Double.parseDouble(precio.getText()));
            
            if(no.isSelected()){
                f.setDisponibilidad(false);
                
            }
            if(fd.update(f)){
                JOptionPane.showMessageDialog(null, "Producto Modificado con exito");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo modificar producto");
            }
        });
        eliminar.addActionListener((ActionEvent e) -> {
            Filtro fd = new Filtro();
            if(fd.delete(codigo.getText())){
                JOptionPane.showMessageDialog(null, "Producto eliminado con exito");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null, "NO fue posible eliminar el Producto");
            }
        });
        buscar.addActionListener((ActionEvent e) -> {
            Filtro fd = new Filtro();
            Producto f = fd.read(codigo.getText());
            if(f== null){
                JOptionPane.showMessageDialog(null, "Producto no se ha encontrado");
            }else{
                codigo.setText(f.getCodigo());
                cantidad.setText(Integer.toString(f.getCantidad()));
                nombre.setText(f.getNombre());
                precio.setText(Double.toString(f.getPrecio()));
                
                
                if(f.getDisponibilidad()){
                    si.setSelected(true);
                }else{
                    no.setSelected(true);
                }
            }
        });
        limpiar.addActionListener((ActionEvent e) -> {
            limpiarCampos();
        });
    }
    public void limpiarCampos(){
        codigo.setText("");
        cantidad.setText("");
        nombre.setText("");
        precio.setText("");
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(() -> {
            new Consulta().setVisible(true);
        });
    }
    
}
