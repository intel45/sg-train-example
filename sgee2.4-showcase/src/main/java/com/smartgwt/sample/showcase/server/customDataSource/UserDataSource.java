/*
 * Isomorphic SmartGWT web presentation layer
 * Copyright 2000 and beyond Isomorphic Software, Inc.
 *
 * OWNERSHIP NOTICE
 * Isomorphic Software owns and reserves all rights not expressly granted in this source code,
 * including all intellectual property rights to the structure, sequence, and format of this code
 * and to all designs, interfaces, algorithms, schema, protocols, and inventions expressed herein.
 *
 *  If you have any questions, please email <sourcecode@isomorphic.com>.
 *
 *  This entire comment must accompany any portion of Isomorphic Software source code that is
 *  copied or moved from this file.
 */

package com.smartgwt.sample.showcase.server.customDataSource;

//----------------------------------------------------------------------
// Custom DataSource example
//
// This class shows how to easily implement a completely customized
// DataSource that simply plugs into the SmartGWT Server framework
//----------------------------------------------------------------------

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.isomorphic.datasource.BasicDataSource;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.util.DataTools;

public class UserDataSource extends BasicDataSource {

    // Override all four CRUD operations - create, retrieve, update and delete
    // (add, fetch, update and remove in SmartClient terminology).

    // Note that the parameters sent by the client arrive here already converted to Java Maps
    // by the SmartClient Server - with SmartClient Pro, Power and Enterprise Editions, there's
    // no need to worry about conversion to and from XML or JSON even in a custom DS
    // implementation

    public DSResponse executeAdd(DSRequest req) throws Exception {
        Map map = req.getValues();
        map = createRecord(map);
        DSResponse resp = new DSResponse();
        resp.setData(map);
        resp.setStatus(0);
        return resp;
    }

    public DSResponse executeFetch(DSRequest req) throws Exception {
        List records = fetchRecords(req.getCriteria());
        DSResponse resp = new DSResponse();
        resp.setData(records);
        resp.setStatus(0);
        return resp;
    }

    public DSResponse executeRemove(DSRequest req) throws Exception {
        Map removedRecord = removeRecord(req.getValues().get(getPrimaryKey()));
        DSResponse resp = new DSResponse();
        if (removedRecord != null) {
            resp.setStatus(DSResponse.STATUS_SUCCESS);
            resp.setData(removedRecord);
        } else {
            resp.setStatus(DSResponse.STATUS_FAILURE);
            resp.setData(req.getOldValues());
        }
        return resp;
    }

    public DSResponse executeUpdate(DSRequest req) throws Exception {
        Map updatedRecord = updateRecord(req.getValues());
        DSResponse resp = new DSResponse();
        if (updatedRecord != null) {
            resp.setStatus(DSResponse.STATUS_SUCCESS);
            resp.setData(updatedRecord);
        } else {
            resp.setStatus(DSResponse.STATUS_FAILURE);
            resp.setData(req.getOldValues());
        }
        return resp;
    }

// -----------------------------------------------------------------------------------------
// Code for actual data creation and manipulation.
//
// You can replace code below to implement any data access approah you actually want to use.
//
// -----------------------------------------------------------------------------------------

    private static List data = new ArrayList();
    private static int nextId;

    // Hard-coded data store
    static {
        String[] userName = { "Charles Madigen", "Ralph Brogan", "Grigori Ognev", "Tamara Kane",
                              "Betsy Rosenbaum", "Gene Porter", "Prya Sambhus", "Ren Xian" };
        String[] jobTitle = { "Chief Operating Officer", "Manager Systems", "Technician",
                              "Manager Site Services", "Secretary", "Manager Purchasing",
                              "Line Worker", "Mobile Equipment Operator" };
        String[] email    = { "charles.madigen", "ralph.brogan", "grigori.ognev", "tamara.kane",
                              "elizabeth.rosenbaum", "gene.porter", "prya.sambhus", "ren.xian" };
        String[] type     = { "full time", "contract", "part time", "full time", "part time",
                              "contract", "part time", "full time" };
        float[] salary      = { 20395, 18076, 12202, 21227, 11632, 17702, 12985, 16402 };

        for (int i = 0; i < userName.length; i++) {
            Map map = new HashMap();
            map.put("employeeId", new Integer(i + 1));
            map.put("userName", userName[i]);
            map.put("job", jobTitle[i]);
            map.put("email", email[i] + "@server.com");
            map.put("employeeType", type[i]);
            map.put("salary", new Float(salary[i]));

            data.add(map);
        }
        nextId = data.size() + 1;
    }

    // Sets autoincrement id and adds new record to list.
    private Map createRecord (Map values) {
        values.put(getPrimaryKey(), new Integer(nextId++));
        data.add(values);
        return values;
    }

    // Returns full data list.
    // This sample does not support server side sorting/filtering.
    private List fetchRecords (Map criteria) {
        return data;
    }

    // Finds record by id. Removes it if found.
    // Returns removed record or null if there was no such record.
    private Map removeRecord (Object id) {
        Number idNum = (Number) id;
        Map map = findById(idNum);
        boolean removed = false;
        if (map != null) {
            removed = data.remove(map);
        }
        if (removed) {
            return map;
        } else {
            return null;
        }
    }

    // Finds record by id. Updates it if found.
    // Returns updated record or null if there was no such record.
    private Map updateRecord (Map values) {
        Number idNum = (Number) values.get(getPrimaryKey());
        Map map = findById(idNum);
        if (map != null) {
            DataTools.mapMerge(values, map);
            return map;
        } else {
            return null;
        }
    }

    // Finds record by id.
    private Map findById (Number id) {
        if (id != null) {
            Integer integerId = id.intValue();
            for (int i = 0; i < data.size(); i++) {
                Map record = (Map)data.get(i);
                if (integerId.equals(record.get(getPrimaryKey()))) {
                    return record;
                }
            }
        }
        return null;
    }

}
