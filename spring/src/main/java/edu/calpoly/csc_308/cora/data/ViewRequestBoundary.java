package edu.calpoly.csc_308.cora.data;

import java.util.Scanner;

import lombok.Data;

import javax.persistence.Entity;


@Data
@Entity
public class ViewRequestBoundary{

    public static String keywords;
    public Volunteer vol;

    public ViewRequestBoundary(Volunteer vol){
        this.vol = vol;
    }

    public static String enterKeyWords(){
        Scanner sc = new Scanner(System.in);

        keywords = sc.nextLine();

        sc.close();

        return keywords;
    }
}