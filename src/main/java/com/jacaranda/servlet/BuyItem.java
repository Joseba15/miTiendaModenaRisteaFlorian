package com.jacaranda.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacaranda.carrito.ItemCart;
import com.jacaranda.carrito.ShoppingCart;
import com.jacaranda.control.ItemCartControl;

/**
 * Servlet implementation class BuyItem
 */
@WebServlet("/buyItem")
public class BuyItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		ItemCartControl daoItemCart = new ItemCartControl();
		LocalDateTime date = LocalDateTime.now();
		
		for(ItemCart aux : cart.getItemList()) {
			aux.setDate(date);
			daoItemCart.addItems(aux);
		}
		session.removeAttribute("cart");
	}

}
