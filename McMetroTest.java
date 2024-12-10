import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class McMetroTest {
    @Test
    void testMaxPassengers() {
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 50)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid2);
        assertEquals(50, maxPassengers);
    }

    @Test
    void testMaxPassengers2() {
        // random graph
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300),
                new Building(bid4, 400)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid2, bid3, 100, 50),
                new Track(new TrackID(3), bid3, bid4, 100, 100),
                new Track(new TrackID(4), bid1, bid3, 100, 25),
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid4);
        assertEquals(75, maxPassengers);
    }

    @Test
    void testMaxPassengers3() {
        // graph with loop
        // random graph
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300),
                new Building(bid4, 400)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid2, bid3, 100, 50),
                new Track(new TrackID(3), bid3, bid4, 100, 100),
                new Track(new TrackID(4), bid3, bid2, 100, 25),
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid4);
        assertEquals(50, maxPassengers);
    }

    @Test
    void testMaxPassengers4() {
        // test disconnected graph
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid3);
        assertEquals(0, maxPassengers);
    }

    @Test
    void testMaxPassengers5() {
        // test disconnected graph
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid2, bid3, 100, 100)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid3, bid1);
        assertEquals(0, maxPassengers);
    }

    @Test
    void testMaxPassengers6() {
        // test disconnected graph
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300)
        };

        Track[] tracks = new Track[]{};

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid3);
        assertEquals(0, maxPassengers);
    }

    @Test
    void testMaxPassengers7() {
        // weird loop
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 100),
                new Building(bid3, 100)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid2, bid3, 100, 100),
                new Track(new TrackID(3), bid3, bid1, 100, 100),

                // other loop
                new Track(new TrackID(4), bid2, bid1, 100, 100),
                new Track(new TrackID(5), bid3, bid2, 100, 100),
                new Track(new TrackID(6), bid1, bid3, 100, 100)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid3);
        assertEquals(200, maxPassengers);
    }

    @Test
    void testMaxPassengers8() {
        // only 1 building
        BuildingID bid1 = new BuildingID(1);
        Building[] buildings = new Building[]{
                new Building(bid1, 100)
        };

        Track[] tracks = new Track[]{};

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid1);
        assertEquals(0, maxPassengers);
    }

    @Test
    void testMaxPassengers9() {
        // only 1 building
        BuildingID bid1 = new BuildingID(1);
        Building[] buildings = new Building[]{
                new Building(bid1, 100)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid1, 100, 100)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid1);
        assertEquals(100, maxPassengers);
    }

    @Test
    void testMaxPassengers10() {
        // long path, but start building has 0 occupants
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);
        BuildingID bid5 = new BuildingID(5);
        BuildingID bid6 = new BuildingID(6);
        BuildingID bid7 = new BuildingID(7);
        BuildingID bid8 = new BuildingID(8);
        BuildingID bid9 = new BuildingID(9);

        Building[] buildings = new Building[]{
                new Building(bid1, 0),
                new Building(bid2, 100),
                new Building(bid3, 200),
                new Building(bid4, 300),
                new Building(bid5, 400),
                new Building(bid6, 500),
                new Building(bid7, 600),
                new Building(bid8, 700),
                new Building(bid9, 800)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid2, bid3, 100, 100),
                new Track(new TrackID(3), bid3, bid4, 100, 100),
                new Track(new TrackID(4), bid4, bid5, 100, 100),
                new Track(new TrackID(5), bid5, bid6, 100, 100),
                new Track(new TrackID(6), bid6, bid7, 100, 100),
                new Track(new TrackID(7), bid7, bid8, 100, 100),
                new Track(new TrackID(8), bid8, bid9, 100, 100)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid9);
        assertEquals(0, maxPassengers);
    }

    @Test
    void testMaxPassengers11() {
        // long path, but end building has 0 occupants
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);
        BuildingID bid5 = new BuildingID(5);
        BuildingID bid6 = new BuildingID(6);
        BuildingID bid7 = new BuildingID(7);
        BuildingID bid8 = new BuildingID(8);
        BuildingID bid9 = new BuildingID(9);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300),
                new Building(bid4, 400),
                new Building(bid5, 500),
                new Building(bid6, 600),
                new Building(bid7, 700),
                new Building(bid8, 800),
                new Building(bid9, 0)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid2, bid3, 100, 100),
                new Track(new TrackID(3), bid3, bid4, 100, 100),
                new Track(new TrackID(4), bid4, bid5, 100, 100),
                new Track(new TrackID(5), bid5, bid6, 100, 100),
                new Track(new TrackID(6), bid6, bid7, 100, 100),
                new Track(new TrackID(7), bid7, bid8, 100, 100),
                new Track(new TrackID(8), bid8, bid9, 100, 100)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid9);
        assertEquals(0, maxPassengers);
    }

    @Test
    void testMaxPassengers12() {
        // 6 buildings
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);
        BuildingID bid5 = new BuildingID(5);
        BuildingID bid6 = new BuildingID(6);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300),
                new Building(bid4, 400),
                new Building(bid5, 500),
                new Building(bid6, 600)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid3, bid4, 100, 100),
                new Track(new TrackID(3), bid4, bid5, 100, 100),
                new Track(new TrackID(4), bid5, bid1, 100, 100),
                new Track(new TrackID(5), bid3, bid6, 100, 25),
                new Track(new TrackID(6), bid6, bid4, 100, 100),
                new Track(new TrackID(7), bid2, bid3, 100, 100),
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid6);
        assertEquals(25, maxPassengers);
    }

    @Test
    void testMaxPassengers13() {
        // 6 buildings
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);
        BuildingID bid5 = new BuildingID(5);
        BuildingID bid6 = new BuildingID(6);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 100),
                new Building(bid3, 100),
                new Building(bid4, 100),
                new Building(bid5, 100),
                new Building(bid6, 100)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid3, bid4, 100, 25),
                new Track(new TrackID(3), bid4, bid5, 100, 100),
                new Track(new TrackID(4), bid5, bid1, 100, 100),
                new Track(new TrackID(5), bid3, bid6, 100, 25),
                new Track(new TrackID(6), bid6, bid4, 100, 100),
                new Track(new TrackID(7), bid2, bid3, 100, 100),
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid4);
        assertEquals(50, maxPassengers);
    }

    @Test
    void testMaxPassengers14() {
        // 6 buildings
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);
        BuildingID bid5 = new BuildingID(5);
        BuildingID bid6 = new BuildingID(6);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 100),
                new Building(bid3, 100),
                new Building(bid4, 100),
                new Building(bid5, 100),
                new Building(bid6, 100)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid3, bid4, 100, 25),
                new Track(new TrackID(3), bid4, bid5, 100, 100),
                new Track(new TrackID(4), bid5, bid1, 100, 100),
                new Track(new TrackID(5), bid3, bid6, 100, 25),
                new Track(new TrackID(6), bid6, bid4, 100, 100),
                new Track(new TrackID(7), bid2, bid3, 100, 100),
                new Track(new TrackID(8), bid2, bid4, 100, 75),
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid4);
        assertEquals(100, maxPassengers);
    }

    @Test
    void testMaxPassengers15() {
        // 6 buildings
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);
        BuildingID bid5 = new BuildingID(5);
        BuildingID bid6 = new BuildingID(6);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 100),
                new Building(bid3, 100),
                new Building(bid4, 100),
                new Building(bid5, 100),
                new Building(bid6, 100)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid3, bid4, 100, 25),
                new Track(new TrackID(3), bid4, bid5, 100, 100),
                new Track(new TrackID(4), bid5, bid1, 100, 100),
                new Track(new TrackID(5), bid3, bid6, 100, 25),
                new Track(new TrackID(6), bid6, bid4, 100, 100),
                new Track(new TrackID(7), bid2, bid3, 100, 100),
                new Track(new TrackID(8), bid2, bid4, 100, 75),
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid4);
        assertEquals(100, maxPassengers);
    }

    @Test
    void testMaxPassengers16() {
        // multiple tracks from same building 1 to 2
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200)
        };

        Track[] tracks = new Track[]{
                new Track(new TrackID(1), bid1, bid2, 100, 100),
                new Track(new TrackID(2), bid1, bid2, 100, 50),
                new Track(new TrackID(3), bid1, bid2, 100, 25),
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        int maxPassengers = mcMetro.maxPassengers(bid1, bid2);
        assertEquals(175, maxPassengers);
    }

    @Test
    void testBestMetro() {
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200)
        };

        TrackID tid = new TrackID(1);
        Track[] tracks = new Track[]{
                new Track(tid, bid1, bid2, 100, 50)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        TrackID[] bms = mcMetro.bestMetroSystem();
        TrackID[] expected = new TrackID[]{tid};
        assertArrayEquals(expected, bms);
    }

    @Test
    void testBestMetro2() {
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300)
        };

        TrackID tid = new TrackID(1);
        TrackID tid2 = new TrackID(2);
        TrackID tid3 = new TrackID(3);
        Track[] tracks = new Track[]{
                new Track(tid, bid1, bid2, 1, 100),
                new Track(tid2, bid2, bid3, 1, 200),
                new Track(tid3, bid1, bid3, 1, 300)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        TrackID[] bms = mcMetro.bestMetroSystem();
        for (TrackID trackID : bms) {
            System.out.println(trackID);
        }
        TrackID[][] expected = {
                new TrackID[]{tid2, tid3},
                new TrackID[]{tid3, tid2},
                new TrackID[]{tid2, tid},
                new TrackID[]{tid, tid2},
        };
        assertTrue(Arrays.equals(expected[0], bms) || Arrays.equals(expected[1], bms) || Arrays.equals(expected[2], bms) || Arrays.equals(expected[3], bms));
    }

    @Test
    void testBestMetro3() {
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);
        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300),
                new Building(bid4, 400)
        };

        TrackID tid = new TrackID(1);
        TrackID tid2 = new TrackID(2);
        TrackID tid3 = new TrackID(3);
        TrackID tid4 = new TrackID(4);
        Track[] tracks = new Track[]{
                new Track(tid, bid1, bid2, 1, 100),
                new Track(tid2, bid2, bid3, 1, 200),
                new Track(tid3, bid1, bid3, 1, 300),
                new Track(tid4, bid3, bid4, 1, 400)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        TrackID[] bms = mcMetro.bestMetroSystem();
        for (TrackID trackID : bms) {
            System.out.println(trackID);
        }
        TrackID[][] expected = {
                new TrackID[]{tid2, tid3, tid4},
                new TrackID[]{tid2, tid, tid4},
        };

        Arrays.sort(bms);
        Arrays.sort(expected[0]);
        Arrays.sort(expected[1]);

        // check if arrays are equal ignoring order
        assertTrue(Arrays.equals(expected[0], bms) || Arrays.equals(expected[1], bms));
    }

    @Test
    void testBestMetro4() {
        // 3 triangle cycle
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);

        Building[] buildings = new Building[]{
                new Building(bid1, 100),
                new Building(bid2, 200),
                new Building(bid3, 300)
        };

        TrackID tid = new TrackID(1);
        TrackID tid2 = new TrackID(2);
        TrackID tid3 = new TrackID(3);

        Track[] tracks = new Track[]{
                new Track(tid, bid1, bid2, 1, 100),
                new Track(tid2, bid2, bid3, 1, 200),
                new Track(tid3, bid3, bid1, 1, 300)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        TrackID[] bms = mcMetro.bestMetroSystem();
        for (TrackID trackID : bms) {
            System.out.println(trackID);
        }
        TrackID[] expected = new TrackID[] {tid2, tid};
        assertArrayEquals(expected, bms);
    }

    @Test
    void testBestMetro5() {
        // only 1 building
        BuildingID bid1 = new BuildingID(1);
        Building[] buildings = new Building[]{
                new Building(bid1, 100)
        };

        Track[] tracks = new Track[]{};

        McMetro mcMetro = new McMetro(tracks, buildings);
        TrackID[] bms = mcMetro.bestMetroSystem();
        TrackID[] expected = new TrackID[]{};
        assertArrayEquals(expected, bms);
    }

    @Test
    void testBestMetro6() {
        // multiple tracks from same building 1 to 2 with other buildnigs
        BuildingID bid1 = new BuildingID(1);
        BuildingID bid2 = new BuildingID(2);
        BuildingID bid3 = new BuildingID(3);
        BuildingID bid4 = new BuildingID(4);

        Building[] buildings = new Building[]{
                new Building(bid1, 1000),
                new Building(bid2, 1000),
                new Building(bid3, 1000),
                new Building(bid4, 1000)
        };

        TrackID tid = new TrackID(1);
        TrackID tid2 = new TrackID(2);
        TrackID tid3 = new TrackID(3);
        TrackID tid4 = new TrackID(4);
        TrackID tid5 = new TrackID(5);
        TrackID tid6 = new TrackID(6);
        TrackID tid7 = new TrackID(7);
        TrackID tid8 = new TrackID(8);
        TrackID tid9 = new TrackID(9);
        TrackID tid10 = new TrackID(10);
        TrackID tid11 = new TrackID(11);
        TrackID tid12 = new TrackID(12);
        TrackID tid13 = new TrackID(13);
        TrackID tid14 = new TrackID(14);
        TrackID tid15 = new TrackID(15);
        TrackID tid16 = new TrackID(16);
        TrackID tid17 = new TrackID(17);
        TrackID tid18 = new TrackID(18);

        Track[] tracks = new Track[]{
                new Track(tid, bid1, bid2, 1, 100),
                new Track(tid2, bid1, bid2, 1, 50),
                new Track(tid3, bid1, bid2, 1, 25),
                new Track(tid4, bid1, bid3, 1, 100),
                new Track(tid5, bid1, bid3, 1, 50),
                new Track(tid6, bid1, bid3, 1, 25),
                new Track(tid7, bid1, bid4, 1, 100),
                new Track(tid8, bid1, bid4, 1, 50),
                new Track(tid9, bid1, bid4, 1, 25),
                new Track(tid10, bid2, bid3, 1, 50),
                new Track(tid11, bid2, bid3, 1, 50),
                new Track(tid12, bid2, bid3, 1, 25),
                new Track(tid13, bid2, bid4, 1, 50),
                new Track(tid14, bid2, bid4, 1, 50),
                new Track(tid15, bid2, bid4, 1, 25)
        };

        McMetro mcMetro = new McMetro(tracks, buildings);
        TrackID[] bms = mcMetro.bestMetroSystem();
        for (TrackID trackID : bms) {
            System.out.println(trackID);
        }
        TrackID[] expected = new TrackID[]{tid, tid4, tid7};
        assertArrayEquals(expected, bms);
    }

    @Test
    void testSearchForPassengers() {
        McMetro mcMetro = new McMetro(new Track[0], new Building[0]);
        String[] passengers = {"Alex", "Bob", "Ally"};
        String[] expected = {"Alex", "Ally"};
        mcMetro.addPassengers(passengers);

        ArrayList<String> found = mcMetro.searchForPassengers("al");

        assertArrayEquals(expected, found.toArray(new String[0]));
    }

    @Test
    void testSearchForPassengers2() {
        McMetro mcMetro = new McMetro(null, null);
        String[] passengers = {"Alex", "Bob", "Ally"};
        String[] expected = {"Alex", "Ally"};
        mcMetro.addPassengers(passengers);

        ArrayList<String> found = mcMetro.searchForPassengers("al");

        assertArrayEquals(expected, found.toArray(new String[0]));
    }

    @Test
    void testHireTicketCheckers() {
        int[][] schedule = new int[4][2];
        schedule[0][0] = 1;
        schedule[0][1] = 2;
        schedule[1][0] = 2;
        schedule[1][1] = 3;
        schedule[2][0] = 3;
        schedule[2][1] = 4;
        schedule[3][0] = 1;
        schedule[3][1] = 3;

        int toHire = McMetro.hireTicketCheckers(schedule);
        assertEquals(3, toHire);
    }

    @Test
    void testHireTicketCheckers2() {
        int[][] schedule = new int[7][2];
        schedule[0][0] = 0;
        schedule[0][1] = 2;
        schedule[1][0] = 1;
        schedule[1][1] = 3;
        schedule[2][0] = 2;
        schedule[2][1] = 5;
        schedule[3][0] = 4;
        schedule[3][1] = 6;
        schedule[4][0] = 5;
        schedule[4][1] = 9;
        schedule[5][0] = 6;
        schedule[5][1] = 9;
        schedule[6][0] = 8;
        schedule[6][1] = 10;

        int toHire = McMetro.hireTicketCheckers(schedule);
        assertEquals(3, toHire);
    }

    @Test
    void testHireTicketCheckers3() {
        int[][] schedule = new int[7][2];
        schedule[0][0] = -1000;
        schedule[0][1] = 20000;
        schedule[1][0] = 1;
        schedule[1][1] = 3;
        schedule[2][0] = 2;
        schedule[2][1] = 5;
        schedule[3][0] = 4;
        schedule[3][1] = 6;
        schedule[4][0] = 5;
        schedule[4][1] = 9;
        schedule[5][0] = 6;
        schedule[5][1] = 9;
        schedule[6][0] = 8;
        schedule[6][1] = 10;

        int toHire = McMetro.hireTicketCheckers(schedule);
        assertEquals(3, toHire);
    }

    @Test
    void testHireTicketCheckers4() {
        int[][] schedule = new int[4][2];
        schedule[0][0] = 1;
        schedule[0][1] = 1;
        schedule[1][0] = 1;
        schedule[1][1] = 1;
        schedule[2][0] = 1;
        schedule[2][1] = 1;
        schedule[3][0] = 1;
        schedule[3][1] = 1;

        int toHire = McMetro.hireTicketCheckers(schedule);
        assertEquals(4, toHire);
    }
}