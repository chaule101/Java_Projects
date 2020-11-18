/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.servlet;

import com.poly.dao.ProductDao;
import com.poly.dao.UserDao;
import com.poly.models.Product;
import com.poly.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanhl
 */
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String type = request.getParameter("_type");
            switch (type) {
                case "DELETE":
                    delete(request, response);
                    break;
                case "UPDATE":
                    int id = Integer.parseInt(request.getParameter("productid"));
                    response.sendRedirect("admin/update_product.jsp?_type=" + type+"&id=" +id);
                    break;
                case "INSERT":
                    insert(request, response);
                    break;
                case "EXE_UPDATE":
                    update(request, response);
                    break;
            }
            //

        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    void insert(HttpServletRequest request, HttpServletResponse response) {
        try {
            int categories_id = Integer.parseInt(request.getParameter("categories_id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            String note = request.getParameter("note");
            String image = request.getParameter("image");

            Product product = new Product(categories_id, name, price, note, image);
            
            ProductDao productdao = new ProductDao();
//             productdao.insertProduct(product);
            boolean result = productdao.insertProduct(product);
            response.sendRedirect("admin/show_product.jsp");
        } catch (Exception e) {
            System.out.print("Lỗi......" + e.toString());
        }
    }

    void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("productid"));
            ProductDao productdao = new ProductDao();
            productdao.deleteProduct(id);
            response.sendRedirect("admin/show_product.jsp");

        } catch (Exception e) {
            System.out.print(e.toString());

        }
    }
    
    void update(HttpServletRequest request, HttpServletResponse response){
          try{
            int id =Integer.parseInt(request.getParameter("id"));
            int categories_id = Integer.parseInt(request.getParameter("Categories_id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            String note = request.getParameter("note");
            String image = request.getParameter("image");
            Product product = new Product( id,categories_id,name,price,note,image);
            ProductDao productdao = new ProductDao();
            boolean result = productdao.updateProduct(product);       
            response.sendRedirect("admin/show_product.jsp");
      } catch (Exception ex) {
              System.out.println("Lỗi......" + ex.toString());
        }
      }

//     <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
