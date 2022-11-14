class NBody {
    // /Users/hbyoung/Programming/Cs61bAudit/proj0/data/planets.txt

    public static String planetsTextFile = "/Users/hbyoung/Programming/Cs61bAudit/proj0/data/planets.txt";
    public static String starfield = "images/starfield.jpg";


    public static Double readRadius(String fileName){
        In PlanetsIn = new In(planetsTextFile);
        PlanetsIn.readLine(); // first line is planet count
		double universeRadius = PlanetsIn.readDouble(); // second line (and next item) is Universe Radius
        return universeRadius;
    }


    public static Planet[] readPlanets(String fileName){
        // Line Reader Object
        In PlanetsIn = new In(fileName);

        // Get number of planets from file
        int planetCount = PlanetsIn.readInt();

        // create array with number of planets
        Planet[] planetArray = new Planet[planetCount];

        PlanetsIn.readDouble(); // Skip this line of text
        
        // For each line(row of planet data)
        for (int i = 0; i < planetCount; i+=1){
            Double[] nextPlanetData = new Double[5];
            
            // read the 5 double data points and append to array
            for (int j = 0; j < 5; j+=1){
                nextPlanetData[j] = PlanetsIn.readDouble();
            }
            // read one string data point
            String planetGif = PlanetsIn.readString();

            // take data points from array and planetGif string and instanciate new planet
            Planet p = new Planet(
                (nextPlanetData[0]),
                (nextPlanetData[1]),
                (nextPlanetData[2]),
                (nextPlanetData[3]),
                (nextPlanetData[4]),
                planetGif);

            // append new planet to array (outside for loop)
            planetArray[i] = p;}
            
                      return planetArray;
                    }

        public static void main(String[] args){
        Double timeLapsed = 0.0;

        Double T = Double.parseDouble(args[0]);
        Double dt = Double.parseDouble(args[1]);
        Planet[] planetArray = readPlanets(args[2]);
        Double uniRad = readRadius(args[2]);

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(250, 250);
        
        StdDraw.setScale(-uniRad, uniRad);
        
        for (Planet p: planetArray){
            p.draw();
            StdDraw.show();
        }

        Double[] xForces = new Double[planetArray.length];
        Double[] yForces = new Double[planetArray.length];



        while (timeLapsed < T){

            for (int i = 0; i < planetArray.length; i += 1){
                xForces[i] = planetArray[i].calcNetForceExertedByX(planetArray);
                yForces[i] = planetArray[i].calcNetForceExertedByY(planetArray);
            }

            for (int j = 0; j < planetArray.length; j += 1){
             planetArray[j].update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0,0, starfield);

            for (Planet p: planetArray){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            timeLapsed += dt;
            

        
        

        }

        StdOut.printf("%d\n", planetArray.length);
        StdOut.printf("%.2e\n", uniRad);
        for (int i = 0; i < planetArray.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planetArray[i].xxPos, planetArray[i].yyPos, planetArray[i].xxVel,
            planetArray[i].yyVel, planetArray[i].mass, planetArray[i].imgFileName);   
}

    }
}

