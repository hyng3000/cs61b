public class Planet {

    // Instance Variables
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName; // name of file that depicts planet ie. jupitor.gif

    // public static final double G = 6.67e-11;
    private final double G = 6.67e-11;

    // Constructor 1
    public Planet(double xP, double yP,
                  double xV, double yV,
                  double m, String img){

        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    // Constructor 2
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    // Static Methods

    private static double exponent(double base, int power){
        //  return an exponent of double type
        double product = 1;
        for (int i = 1; i <= power; i+=1){
            product *= base;
        }
        return product;
    }

    private static double square(double n){
        return n * n;
    }

    // Methods

    // Distance
    public double calcDistance(Planet p){
            // r^2=dx^2+dy^2
            // return a double for the dist. 'r' between the supplied planet and this.Planet
            double xVal = p.xxPos - this.xxPos;
            double yVal = p.yyPos - this.yyPos;
            double r = Math.sqrt(square(xVal) + square(yVal));
            
            return r;
    }

    // Force

    public double calcForceExertedBy(Planet p) {
        // F= (G⋅m1⋅m2) /r^2
        // Graviational constant 6.67 * 10^-11 (Approx value)
		double dist = calcDistance(p);
		return G * mass * p.mass / square(dist);
	}

    public double calcForceExertedByX(Planet p){
        // Fx = (F ⋅ dx) / r
        double F = calcForceExertedBy(p);
        double dX = p.xxPos - this.xxPos;
        double r = calcDistance(p);
        return (F * dX) / r;
    }

    public double calcForceExertedByY(Planet p){
        // Fy = (F ⋅ dy) / r
        double F = calcForceExertedBy(p);
        double dY = p.yyPos - this.yyPos;
        double r = calcDistance(p);
        return F * dY / r;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double totalX = 0;

        for (Planet p: planets){
            if (p != this){
            totalX += calcForceExertedByX(p);
            }
        }
        return totalX;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double totalY = 0;

        for (Planet p: planets){
            if (p != this){
            totalY += calcForceExertedByY(p);
            }
        }
        return totalY;
    }

    // Acceleration

    private double calcAcceleration(double force){
        return force / this.mass;
    }


    // Velocity

    private double calcNewVelocityX(double dT, double aX){
        return this.xxVel + dT * aX;
    }

    private double calcNewVelocityY(double dT, double aY){
        return this.yyVel + dT * aY;
    }

    // Position

    private double newXPos(double dT, double xVel){
        return this.xxPos + dT * xVel;
    }

    private double newYPos(double dT, double yVel){
        return this.yyPos + dT * yVel;
    }

    // Update

    public void update(double dt, double fX, double fY){
        double accelX = calcAcceleration(fX);
        double accelY = calcAcceleration(fY);

        this.xxVel = calcNewVelocityX(dt, accelX);
        this.yyVel = calcNewVelocityY(dt, accelY);

        this.xxPos = newXPos(dt, this.xxVel);
        this.yyPos = newYPos(dt, this.yyVel);

    }

    public void draw(){
        String file = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos,this.yyPos, file);
    }


}








