package org.seeker.finallys;

public class Test {

    /**
     * @return
     */
    public String a(){
        try {
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

}
