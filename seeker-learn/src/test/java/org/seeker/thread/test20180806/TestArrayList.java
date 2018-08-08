package org.seeker.thread.test20180806;

import java.util.ArrayList;
import java.util.List;

public class

TestArrayList

{

    private

    static

    List<
            Integer
            >
            list
            =

            new

                    ArrayList<>();

    public

    static void
    main
            (
                    String
                            []
                            args
            )

            throws

            InterruptedException

    {

        for

                (
                int
                i
                =

                0
                ;
                i
                        <

                        10
                ;
                i
                        ++)

        {
            testList
                    ();
            list
                    .
                            clear
                                    ();

        }

    }

    private

    static void
    testList
            ()

            throws

            InterruptedException

    {

        Runnable
                runnable
                =

                ()

                        ->

                {

                    for

                            (
                            int
                            i
                            =

                            0
                            ;
                            i
                                    <

                                    10000
                            ;
                            i
                                    ++)

                    {
                        list
                                .
                                        add
                                                (
                                                        i
                                                );

                    }

                };

        Thread
                t1
                =

                new

                        Thread
                        (
                                runnable
                        );

        Thread
                t2
                =

                new

                        Thread
                        (
                                runnable
                        );

        Thread
                t3
                =

                new

                        Thread
                        (
                                runnable
                        );
        t1
                .
                        start
                                ();
        t2
                .
                        start
                                ();
        t3
                .
                        start
                                ();
        t1
                .
                        join
                                ();
        t2
                .
                        join
                                ();
        t3
                .
                        join
                                ();

        System
                .
                        out
                .
                        println
                                (
                                        list
                                                .
                                                        size
                                                                ());

    }
}