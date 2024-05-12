package com.evoting.Service;

import java.util.*;
import java.util.stream.Collectors;

public class xyz {
    public static void main(String[] args) {


        amazonQ1();
       // amazonQ2();

        }
        public static void amazonQ1()
        {
            String str = "adbccdbada";
            Set<Character> setp = new HashSet<>();
            Set<Character> sets= new HashSet<>();

            int k=2;
            int cnt =0;
            for (int i = 0; i < str.length() - 1; i++) {
                String sp = str.substring(0, i + 1);
                String sfx = str.substring(i + 1, str.length());
                setp.clear();
                sets.clear();


              setp=sp.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
              sets = sfx.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
              int setpsize= setp.size();
              int setssize= sets.size();
              if(setpsize<setssize )
              {
                if(setpsize>k)
                {
                    cnt++;
                }

              }
              else if(sets.size()==setp.size())
              {
                 cnt++;
              }
              else
              {
                  if(sets.size()>k) cnt++;
              }

            }

            System.out.println(cnt);




//
//            int k = 2;
//            int sum = 0;
//            List<Character> lip = new ArrayList<>();
//            List<Character> lis = new ArrayList<>();
//
//            ArrayList<Integer> result= new ArrayList<>();
//
//
//            for (int i = 0; i < prefix.size(); i++) {
//
//
//                lip.clear();
//                lis.clear();
//
//                //prefix
//                String pre = prefix.get(i);
//                // System.out.println(pre);
//                for (int x = 0; x < pre.length(); x++) {
//                    char ch = pre.charAt(x);
//                    if (!lip.contains(ch)) {
//                        lip.add(ch);
//                    }
//                }
//
//                //suffix
//                String suf = suffix.get(i);
//                // System.out.println(suf);
//                for (int y = 0; y < suf.length(); y++) {
//                    char chs = suf.charAt(y);
//                    if (!lis.contains(chs)) {
//                        lis.add(chs);
//                    }
//                }
//                System.out.println(lip + "-->" + pre);
//                System.out.println(lis + "-->" + suf);
//
//
//                if(lip.size()<lis.size())
//                {
//                    result.add(lip.size());
//                }
//                else if(lis.size()==lip.size())
//                {
//                    result.add(lis.size());
//                }
//                else {
//                    result.add(lis.size());
//                }
//
//
//            }
//            int resoolt=0;
//            for(int m=0;m<result.size();m++)
//            {
//                if(k<result.get(m))
//                {
//                    resoolt++;
//                }
//            }
//
//            System.out.println(resoolt);
//            System.out.println(result);

        }

        public static void amazonQ2()
        {

            int arr[]= {3,6,3};

            // bs we need to
            //first find the subarrays then combine to get the complete array

            for(int i=0;i<arr.length;i++)
            {
                for(int j=i;j<arr.length;j++)
                {

                    List<Integer> li = new ArrayList<>();

                    for(int k=i;k<=j;k++)
                    {
                        li.add(arr[k]);
                    }
                    System.out.println(li);
                }
            }


        }
    }





