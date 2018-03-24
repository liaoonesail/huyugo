package com.huyu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Address;
import com.huyu.entity.User;
import com.huyu.service.AddressService;
import com.huyu.util.Utils;

@Controller
@RequestMapping("/address")
public class AddressController {

	private AddressService addressService;

	public AddressService getAddressService() {
		return addressService;
	}
	@Resource
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	/**
	 * 显示收货地址
	 * @param request
	 * @return
	 */
	@RequestMapping("/showAddress")
	public String showAddress(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List<Address> addressList = addressService.list(user.getId());
		request.setAttribute("addressList", addressList);
		return "memberCenter/right/address";
	}
	
	/**
	 * 添加收货地址
	 * @param address
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAddress")
	public String addAddress(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		String status = (String)request.getParameter("status");
		if(status != null && "add".equals(status)){
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String county = request.getParameter("county");
			String address_ = request.getParameter("address");
			Address address = new Address();
			address.setUser_id(user.getId());
			address.setCity(city);
			address.setCounty(county);
			address.setName(name);
			address.setPhone(phone);
			address.setProvince(province);
			address.setAddress(address_);
			addressService.add(address);
			return "redirect:showAddress.do";
		}else{
			return "memberCenter/right/addAddress";
		}
	}
	
	/**
	 * 根据ID删除收货地址
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAddress")
	public String deleteAddress(HttpServletRequest request){
		
		String idstr = (String)request.getParameter("id");
		int id = 0;
		if(idstr != null && Utils.isNumeric(idstr)){
			id = Integer.parseInt(idstr);
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if(user == null){
				return "redirect:showAddress.do";
			}else{
				Address address = addressService.getid(id);
				if(address.getUser_id() != user.getId()){
					return "redirect:showAddress.do";
				}
			}
			if(id > 0){
				addressService.del(id);
			}
		}
		return "redirect:showAddress.do";
	}
	
	/**
	 * 修改收货地址
	 * @param address
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAddress")
	public String updateAddress(HttpServletRequest request){
		HttpSession session = request.getSession();
		String status = request.getParameter("status");
		Address address = null;
		String idstr = (String)request.getParameter("id");
		int id = 0;
		if(idstr != null && Utils.isNumeric(idstr)){
			id = Integer.parseInt(idstr);
			if(id > 0){
				address = addressService.getid(id);
			}
		}
		if(status == null || "".equals(status)){
			
			session.setAttribute("address", address);
			return "memberCenter/right/updateAddress";
		}else{
			User user = (User)session.getAttribute("user");
			if(user == null){
				return "redirect:showAddress.do";
			}else{
				if(address == null || address.getUser_id() != user.getId()){
					return "redirect:showAddress.do";
				}
			}
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String county = request.getParameter("county");
			String address2 = request.getParameter("address");
			
			address.setUser_id(user.getId());
			address.setCity(city);
			address.setCounty(county);
			address.setName(name);
			address.setPhone(phone);
			address.setProvince(province);
			address.setAddress(address2);
			
			address.setUser_id(user.getId());
			addressService.update(address);
		}
		return "redirect:showAddress.do";
	}
	
	/**
	 * 修改默认地址
	 * @param request
	 * @return
	 */
	@RequestMapping("/moren")
	public String moren(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String idStr = (String)request.getParameter("id");
		int id = 0;
		if(user != null){
			if(idStr != null && Utils.isNumeric(idStr)){
				id = Integer.parseInt(idStr);
				Address address = addressService.getid(id);
				if(address.getUser_id() == user.getId()){
					addressService.update(address);
				}
			}
		}
		return "redirect:showAddress.do";
	}
}
