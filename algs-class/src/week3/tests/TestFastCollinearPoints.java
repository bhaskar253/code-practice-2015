package week3.tests;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import week3.FastCollinearPoints;
import week3.LineSegment;
import week3.Point;

import edu.princeton.cs.algs4.In;
import static org.junit.Assert.*;

public class TestFastCollinearPoints {
    
    private static final String PATH = System.getenv("TEST_RESOURCES");
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void Test5HorizontalLines() {
        final String testFile = PATH + "algs-class/horizontal5.txt";
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        LineSegment[] result = collinearPoints.segments();
        assertEquals(5, result.length);
    }
    
    @Test
    public void TestExceptionOnDuplicatePoint() {
        final String testFile = PATH + "same-point.txt";
        Point[] points = readTestFile(testFile);
        exception.expect(IllegalArgumentException.class);
        @SuppressWarnings("unused")
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
    }
    
    @Test
    // tests combinations of points that don't make a 4-point line
    public void TestNoLineSegments(){
        test1Point();
        test1Point();
        test2Points();
        test3HorizontalPoints();
        test3VerticalPoints();
        test3DiagonalPoints();
    }
    
    @Test
    // tests a line made of 4 points
    public void Test1LineSegment() {
        test4HorizontalPoints();
        test4VerticalPoints();
        test4DiagPoints();
    }
    
    @Test
    // tests 2 lines made of 4 points, that are parallel to one another
    public void Test2ParalelLineSegments() {
        test2ParallelHorizontalSegments();
        test2ParallelVerticalSegments();
        test2ParallelDiagonalSegments();
    }
    
    @Test
    // tests 2 lines made of 4 points, that intersect to one another
    public void Test2PerpendicularLineSegments() {
        test2IntersectingSegments90deg();
        test2IntersectingSegments45deg();
    }
    
    @Test
    public void Test5PointSegments() {
        test5HorizontalPoints();
        test5VerticalPoints();
        test5DiagonalPoints();
    }
    
    @Test
    // Checks that data structure doesn't change state of arguments
    public void TestDoesntMutate() {
        Point[] originalInput = new Point[] {
                new Point(0, 3),
                new Point(0, 2),
                new Point(0, 1),
                new Point(0, 0),
        };
        Point[] input = Arrays.copyOf(originalInput, originalInput.length);
        @SuppressWarnings("unused")
        FastCollinearPoints collinearPoints = new FastCollinearPoints(input);
        assertTrue("Data structure shouldn't mutate input",
                    isSameArray(originalInput, input));
    }
    
    private void test1Point(){
        final String testFile = PATH + "input1.txt";
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        assertEquals(0, collinearPoints.numberOfSegments());
    }
    private void test2Points(){
        final String testFile = PATH + "input2.txt";
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        assertEquals(0, collinearPoints.numberOfSegments());
    }
    private void test3HorizontalPoints(){
        final String testFile = PATH + "input3-horizontal.txt";
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        assertEquals(0, collinearPoints.numberOfSegments());
    }
    private void test3VerticalPoints(){
        final String testFile = PATH + "input3-vertical.txt";
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        assertEquals(0, collinearPoints.numberOfSegments());
    }
    private void test3DiagonalPoints(){
        final String testFile = PATH + "input3-diag.txt";
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        assertEquals(0, collinearPoints.numberOfSegments());
    }
    
    private void test4HorizontalPoints(){
        final String testFile = PATH + "input4-horizontal.txt";
        final Point p = new Point(-1, 0);
        final Point q = new Point(2, 0);
        final LineSegment expectedSegment = new LineSegment(p, q);
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(1, collinearPoints.numberOfSegments());
        LineSegment result = collinearPoints.segments()[0];
        assertTrue(String.format("Expected %s, but was %s", expectedSegment, result),
                isSameLineSegment(expectedSegment, result));
        

    }
    private void test4VerticalPoints(){
        final String testFile = PATH + "input4-vertical.txt";
        final Point p = new Point(0, -1);
        final Point q = new Point(0, 2);
        final LineSegment expectedSegment = new LineSegment(p, q);
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(1, collinearPoints.numberOfSegments());
        assertTrue(isSameLineSegment(expectedSegment, collinearPoints.segments()[0]));
    }
    
    private void test4DiagPoints(){
        final String testFile = PATH + "input4-diag.txt";
        final Point p = new Point(-1, -1);
        final Point q = new Point(2, 2);
        final LineSegment expectedSegment = new LineSegment(p, q);
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(1, collinearPoints.numberOfSegments());
        assertTrue(isSameLineSegment(expectedSegment, collinearPoints.segments()[0]));
    }
    
    private void test2ParallelHorizontalSegments() {
        final String testFile = PATH + "input4-parallel-horizontal-lines.txt";
        final LineSegment[] segments = new LineSegment[] {
                new LineSegment(new Point(-1, 0), new Point(2, 0)),
                new LineSegment(new Point(-1, 1), new Point(2, 1))
        };
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(2, collinearPoints.numberOfSegments());
        assertTrue(areSameSegments(segments, collinearPoints.segments()));
    }
    private void test2ParallelVerticalSegments() {
        final String testFile = PATH + "input4-parallel-vertical-lines.txt";
        final LineSegment[] segments = new LineSegment[] {
                new LineSegment(new Point(0, -1), new Point(0, 2)),
                new LineSegment(new Point(1, -1), new Point(1, 2))
        };
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(2, collinearPoints.numberOfSegments());
        assertTrue(areSameSegments(segments, collinearPoints.segments()));
    }
    private void test2ParallelDiagonalSegments() {
        final String testFile = PATH + "input4-parallel-diagonal-lines.txt";
        final LineSegment[] segments = new LineSegment[] {
                new LineSegment(new Point(-1, -1), new Point(2, 2)),
                new LineSegment(new Point(-1, -2), new Point(2, 1))
        };
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(2, collinearPoints.numberOfSegments());
        assertTrue(areSameSegments(segments, collinearPoints.segments()));
    }
    
    private void test2IntersectingSegments90deg(){
        final String testFile = PATH + "input4-intersecting-90deg.txt";
        final LineSegment[] segments = new LineSegment[] {
                new LineSegment(new Point(-1, 0), new Point(2, 0)),
                new LineSegment(new Point(0, -1), new Point(0, 2))
        };
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(2, collinearPoints.numberOfSegments());
        assertTrue(areSameSegments(segments, collinearPoints.segments()));
    }
    private void test2IntersectingSegments45deg(){
        final String testFile = PATH + "input4-intersecting-45deg.txt";
        final LineSegment[] expectedSegments = new LineSegment[] {
                new LineSegment(new Point(0, -1), new Point(0, 2)),
                new LineSegment(new Point(-1, 1), new Point(2, -2))
        };
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(2, collinearPoints.numberOfSegments());
        LineSegment[] resultSegments = collinearPoints.segments();
        assertTrue(String.format("Expected %s, but was %s", Arrays.toString(expectedSegments),
                                                            Arrays.toString(resultSegments)),
                    areSameSegments(expectedSegments, resultSegments));
    }
    
    private void test5HorizontalPoints(){
        final String testFile = PATH + "input5-horizontal.txt";
        final Point p = new Point(-1, 0);
        final Point q = new Point(3, 0);
        final LineSegment expectedSegment = new LineSegment(p, q);
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(1, collinearPoints.numberOfSegments());
        assertTrue(isSameLineSegment(expectedSegment, collinearPoints.segments()[0]));
    }
    private void test5VerticalPoints(){
        final String testFile = PATH + "input5-vertical.txt";
        final Point p = new Point(0, -1);
        final Point q = new Point(0, 3);
        final LineSegment expectedSegment = new LineSegment(p, q);
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(1, collinearPoints.numberOfSegments());
        assertTrue(isSameLineSegment(expectedSegment, collinearPoints.segments()[0]));
    }
    private void test5DiagonalPoints(){
        final String testFile = PATH + "input5-diag.txt";
        final Point p = new Point(-1, -1);
        final Point q = new Point(3, 3);
        final LineSegment expectedSegment = new LineSegment(p, q);
        
        Point[] points = readTestFile(testFile);
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        
        assertEquals(1, collinearPoints.numberOfSegments());
        assertTrue(isSameLineSegment(expectedSegment, collinearPoints.segments()[0]));
    }
    
    private Point[] readTestFile(String filename) {
        // read the N points from a file
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }
    
    private boolean areSameSegments(LineSegment[] arr1, LineSegment[] arr2) {
        if(arr1.length != arr2.length) {
            return false;
        } else {
            boolean sameSegment = false;
            for (int i = 0; i < arr1.length; i++) {
                sameSegment = false;
                for (int j = 0; j < arr2.length; j++) {
                    if(isSameLineSegment(arr1[i], arr2[j])) {
                        sameSegment = true;
                        break;
                     }
                }
                if(!sameSegment) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isSameLineSegment(LineSegment s1, LineSegment s2) {
        return s1.compareTo(s2) == 0;
    }
    
    private boolean isSameArray(Point[] arr1, Point[] arr2) {
        if(arr1 == null || arr2 == null) {
            return false;
        }
        if(arr1.length != arr2. length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i].compareTo(arr2[i]) != 0) {
                return false;
            }
        }
        return true;
    }

}
