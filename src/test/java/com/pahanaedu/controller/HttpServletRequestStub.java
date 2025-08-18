package com.pahanaedu.controller;

import javax.servlet.RequestDispatcher;

public abstract class HttpServletRequestStub {
    public abstract String getParameter(String name);

    public abstract RequestDispatcher getRequestDispatcher(String path);
}
