package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = req.getParameter("action");
        if(action!=null) {
            if(action.equals("delete")) {
                log.debug("entered delete meal action. meailId = "+req.getParameter("mealid"));
                deleteMeal(Integer.parseInt(req.getParameter("mealid")));
                resp.sendRedirect("/meals");
                log.debug("meal deleted successfully");
                return;
            }
        }

        req.setAttribute("meals", getMeals());

        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doPost entered");
        String action = req.getParameter("button");
        if(action!=null){
            if(action.equals("Add")){
                try {
                    String description = req.getParameter("addDescription");
                    int addCalories = Integer.parseInt(req.getParameter("addCalories"));
                    LocalDateTime addDate = LocalDateTime.parse(req.getParameter("addDate"));

                    MealRepository.addMeal(addDate, description, addCalories);

                    resp.sendRedirect("/meals");
                    log.debug("new meal added. sendRedirect to /meals");
                    return;
                } catch (Exception e)
                {
                    log.error("Exception thrown in doPost of MealServlet: " + e.getMessage());
                    resp.sendRedirect("/meals");
                    return;
                }
            } else if (action.equals("Update")){

            }
        }
    }


    private List<MealWithExceed> getMeals(){
        return MealsUtil.getFilteredWithExceeded(MealRepository.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    public void deleteMeal(int id){
        MealRepository.removeMeal(id);
    }

    public void addMeal(){

    }
}
