package com.example.nikola.testapp.models.parser;


import android.content.Context;
import android.util.Log;

import com.example.nikola.testapp.Settings.Constants;
import com.example.nikola.testapp.Settings.MyApplication;
import com.example.nikola.testapp.adapters.categoryAdapter.Item;
import com.example.nikola.testapp.models.pojo.AppTerm;
import com.example.nikola.testapp.models.pojo.TeamCategory;
import com.example.nikola.testapp.models.pojo.TeamTerms;
import com.example.nikola.testapp.models.pojo.TournamentTerms;
import com.example.nikola.testapp.models.pojo.TournamentUnique;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Deni Slunjski on 2.2.2017..
 */

public class TermsParser extends DefaultHandler {

    private Context context;
    private StringBuilder sb = null;
    private String temp;
    private String temp1;

    private final ArrayList<TeamCategory> teamCategories = new ArrayList<>();
    private final LinkedHashMap<String, TeamTerms> teamTermses = new LinkedHashMap<>();
    private final LinkedHashMap<String, TournamentTerms> tournamentTermses = new LinkedHashMap<>();
    private final LinkedHashMap<String, AppTerm> appTerms = new LinkedHashMap<>();
    private final LinkedHashMap<String, TournamentUnique> stringTournamentUniqueLinkedHashMap = new LinkedHashMap<>();

    private int wichTerms = 0;


    public void parseXmlFile(StringBuffer dataToParse) throws Exception {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser mSaxParser = factory.newSAXParser();
            XMLReader mXmlReader = mSaxParser.getXMLReader();
            mXmlReader.setContentHandler(this);

            InputSource is = new InputSource(new StringReader(new String(dataToParse)));
            mXmlReader.parse(is);

            Log.d("ispis", "ispis" + is);
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (sb != null) {
            for (int i = start; i < start + length; i++) {
                sb.append(ch[i]);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {


        if (localName.equals("teamIconURL"))
            MyApplication.getInstance().set(Constants.teamIconUrl, sb.toString().trim());
        if (localName.equals("tournamentIconURL"))
            MyApplication.getInstance().set(Constants.tournamentIconURL, sb.toString().trim());
        if (localName.equals("teamCategory")) {
            MyApplication.getInstance().set(Constants.teamCategories, teamCategories);
        } else if (localName.equals("teamTerms")) {
            MyApplication.getInstance().set(Constants.teamTermses, teamTermses);
        } else if (localName.equals("tournamentTerms")) {
            MyApplication.getInstance().set(Constants.tournamentTermses, tournamentTermses);
        } else if (localName.equals("appTerms")) {
            MyApplication.getInstance().set(Constants.appTerms, appTerms);
        } else if (localName.equals("tournamentUnique")) {
            MyApplication.getInstance().set(Constants.stringTournamentUniqueLinkedHashMap, stringTournamentUniqueLinkedHashMap);
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //Log.i(TAG, "TAG: " + localName);
        sb = new StringBuilder();

        if (localName.equals("appTerms")) {
            wichTerms = Constants.appTerms1;
        }
        if (localName.equals("Term") && wichTerms == Constants.appTerms1) {

            AppTerm appTerm = new AppTerm();
            if (attributes.getValue("term_id") != null) {
                appTerm.setTermID(attributes.getValue("term_id"));
            }
            if (attributes.getValue("term") != null) {
                appTerm.setTerm(attributes.getValue("term"));
                appTerms.put(attributes.getValue("term_id"), appTerm);
            }
        }


        if (localName.equals("teamCategory")) {
            wichTerms = Constants.teamCategory1;
        }

        if (localName.equals("Term") && wichTerms == Constants.teamCategory1) {
            TeamCategory teamCategory = new TeamCategory();
            teamCategory.setRecyclerType(Item.TYPE_CATEGORY);
            if (attributes.getValue("category_id") != null) {
                teamCategory.setCategoryID(Integer.parseInt(attributes.getValue("category_id")));
            }
            if (attributes.getValue("term") != null) {
                teamCategory.setTerm(attributes.getValue("term"));
            }
            if (attributes.getValue("term") != null) {
                teamCategories.add(teamCategory);
            }
        }

        if (localName.equals("teamTerms")) {
            wichTerms = Constants.teamTerms1;
        }

        if (localName.equals("Term") && wichTerms == Constants.teamTerms1) {

            TeamTerms teamTerms = new TeamTerms();
            if (attributes.getValue("team_id") != null) {
                teamTerms.setTeamID(Integer.parseInt(attributes.getValue("team_id")));
            }
            if (attributes.getValue("term") != null) {
                teamTerms.setTerm(attributes.getValue("term"));
            }
            if (attributes.getValue("country_iso") != null) {
                teamTerms.setCountryISO(attributes.getValue("country_iso"));
            }
            if (attributes.getValue("ts") != null) {
                teamTerms.setTs(attributes.getValue("ts"));
            }
            if (attributes.getValue("country_iso") != null) {
                teamTermses.put(attributes.getValue("team_id"), teamTerms);
            }

        }

        if (localName.equals("tournamentTerms")) {
            wichTerms = Constants.torunamentTerms1;
        }
        if (localName.equals("Term") && wichTerms == Constants.torunamentTerms1) {

            TournamentTerms tournamentTerms = new TournamentTerms();
            if (attributes.getValue("tournament_id") != null) {
                tournamentTerms.setTournamentID(Integer.parseInt(attributes.getValue("tournament_id")));
            }
            if (attributes.getValue("term") != null) {
                tournamentTerms.setTerm(attributes.getValue("term"));
            }
            if (attributes.getValue("ts") != null) {
                tournamentTerms.setTs(attributes.getValue("ts"));
            }
            if (attributes.getValue("term") != null) {
                tournamentTermses.put(attributes.getValue("tournament_id"), tournamentTerms);
            }
        }

        if (localName.equals("tournamentUnique")) {
            wichTerms = Constants.tournamentUnique;
        }
        if (localName.equals("Term") && wichTerms == Constants.tournamentUnique) {

            TournamentUnique tournamentUnique = new TournamentUnique();
            if (attributes.getValue("TourUID") != null) {
                tournamentUnique.setTourUID(Integer.parseInt(attributes.getValue("TourUID")));
            }
            if (attributes.getValue("term") != null) {
                tournamentUnique.setTerm(attributes.getValue("term"));
                stringTournamentUniqueLinkedHashMap.put(attributes.getValue("TourUID"), tournamentUnique);
            }
        }

    }

}