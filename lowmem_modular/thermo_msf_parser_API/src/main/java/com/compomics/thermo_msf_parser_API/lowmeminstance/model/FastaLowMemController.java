/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compomics.thermo_msf_parser_API.lowmeminstance.model;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davy
 */
public class FastaLowMemController {
    private static final Logger logger = Logger.getLogger(FastaLowMemController.class);

    public List<String> getFastaFileNames(Connection msfFileConnection) {
        List<String> iFastaFiles = new ArrayList<String>();
        try {
            Statement stat = msfFileConnection.createStatement();
            ResultSet rs = stat.executeQuery("select VirtualFileName as file from fastafiles");
            while (rs.next()) {
                iFastaFiles.add(rs.getString("file"));
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return iFastaFiles;
    }

    public int getNumberOfProteinsInFastaFile(Connection msfFileConnection,String fastaFileName) {
        int numberOfProteins = 0;
        try {
            PreparedStatement stat = msfFileConnection.prepareStatement("select NumberOfProteins from fastafiles where VirtualFileName = ?");
            stat.setString(1, fastaFileName);
            ResultSet rs = stat.executeQuery();
            rs.next();
            numberOfProteins =rs.getInt("NumberOfProteins");
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return numberOfProteins;
    }
    
    public int getNumberOfAminoAcidsInFastaFile(Connection msfFileConnection, String fastaFileName) {
            int numberOfAminoAcids = 0;
        try {
            PreparedStatement stat = msfFileConnection.prepareStatement("select NumberOfAminoAcids from fastafiles where VirtualFileName = ?");
            stat.setString(1,fastaFileName);
            ResultSet rs = stat.executeQuery();
            rs.next();
            numberOfAminoAcids =rs.getInt("NumberOfAminoAcids");
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return numberOfAminoAcids;
    }
}
