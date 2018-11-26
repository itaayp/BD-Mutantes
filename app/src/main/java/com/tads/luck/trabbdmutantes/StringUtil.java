package com.tads.luck.trabbdmutantes;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<Skill> retornarSkills(String skills){
        List<Skill> skillsList = new ArrayList<Skill>();
        Skill skill;
        for(String s : skills.split(";") ){
            skill = new Skill();
            skill.setSkillName(s);
            skillsList.add(skill);
        }
        return skillsList;
    }
}
