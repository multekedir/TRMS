package com.revature.data;

import com.revature.models.Form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.SQLBuilder.insertInto;

public class FormDAO extends DAO<Form> {

    private static final String TABLE_NAME = "forms";

    @Override
    Form setData(ResultSet rs) throws SQLException {
        getLogger(FormDAO.class).info("Setting role data");
        Form form = new Form(rs);
        getLogger(FormDAO.class).debug(form);
        return form;
    }

    @Override
    PreparedStatement extractData(PreparedStatement ps, Form form) throws SQLException {
        getLogger(FormDAO.class).debug("Extracting role data");
        assert (ps != null & form != null);
        ps.setInt(1, form.getSubmittedBY());
        ps.setDouble(2, form.getAmount());
        ps.setString(3, form.getDescription());
        return ps;
    }

    @Override
    void extractID(Form form, ResultSet rs) throws SQLException {
        if (rs.next()) {
            getLogger(FormDAO.class).info("Extracting from ID");
            form.setId(rs.getInt(1));
        }
    }


    @Override
    public boolean insert(Form form) {
        String sql = insertInto(TABLE_NAME, "submitted_by", "amount", "description");
        getLogger(FormDAO.class).debug("Adding " + form);
        return super.insert(form, TABLE_NAME, sql);
    }

    public Form getByID(int id) {

        getLogger(FormDAO.class).info("Getting form using ID");
        return super.getById(id, TABLE_NAME);
    }

    public Set<Form> filter(String column, String status) {
        getLogger(FormDAO.class).info("Filtering requests");
        return super.getFiltered(TABLE_NAME, column, status);
    }

    public Set<Form> filter(String column, int status) {
        getLogger(FormDAO.class).info("Filtering requests");
        return super.getFiltered(TABLE_NAME, column, status);
    }


    @Override
    Form update(Form form) throws SQLException {
        return null;
    }


}
