
// Use Canvas to render a mesh gradient, passing the rendering to an image via a data stream.
// Copyright: Tavmjong Bah 2017
// Distributed under GNU General Public License version 3 or later. See <http://fsf.org/>.

(function() {

    var counter = 0;  // Temp, number of calls to Canvas

    // Name spaces -----------------------------------
    var svgNS    = "http://www.w3.org/2000/svg";
    var xlinkNS  = "http://www.w3.org/1999/xlink"
    var xhtmlNS  = "http://www.w3.org/1999/xhtml";

    // Test if mesh gradients are supported.
    var m = document.createElementNS( svgNS, "meshgradient" );
    if (m.x) {
        return;
    }

    // Test above test using known good SVG element
    // var l = document.createElementNS( svgNS, "linearGradient" );
    // if (l.x1) {
    // 	console.log( "linearGradient has x1" );
    // 	return;
    // } else {
    // 	console.log( "linearGradient does not have x1" );
    // }

    // Point class -----------------------------------
    function Point(x, y) {
        this.x = x || 0;
        this.y = y || 0;
    };

    Point.prototype.x = null;
    Point.prototype.y = null;

    Point.prototype.get_x = function() {
        return this.x;
    }

    Point.prototype.get_y = function() {
        return this.y;
    }

    Point.prototype.clone = function() {
        return new Point(this.x, this.y);
    }

    Point.prototype.add = function(v) {
        return new Point(this.x + v.x, this.y + v.y);
    };

    Point.prototype.scale = function(v) {
        if( v instanceof Point ) {
            return new Point(this.x * v.x, this.y * v.y);
        }
        return new Point(this.x * v, this.y * v);
    };

    // Transform by affine
    Point.prototype.transform = function(a) {
        var x = this.x * a.a + this.y * a.c + a.e;
        var y = this.x * a.b + this.y * a.d + a.f;
        return new Point(x, y);
    };

    Point.prototype.dist_sq = function(v) {
        var x = this.x - v.x;
        var y = this.y - v.y;
        return (x*x + y*y);
    };

    Point.prototype.toString = function() {
        return "(x=" + this.x + ", y=" + this.y + ")";
    };

    // Affine class -----------------------------------

    // As defined in the SVG spec
    // | a  c  e |
    // | b  d  f |
    // | 0  0  1 |
    function Affine(a, b, c, d, e, f) {
        if (a === undefined) {
            this.a = 1;
            this.b = 0;
            this.c = 0;
            this.d = 1;
            this.e = 0;
            this.f = 0;
        } else {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
        }
    };

    Affine.prototype.a = null;
    Affine.prototype.b = null;
    Affine.prototype.c = null;
    Affine.prototype.d = null;
    Affine.prototype.e = null;
    Affine.prototype.f = null;

    Affine.prototype.append = function(v) {
        if ( !(v instanceof Affine) ) {
            console.log ( "mesh.js: argument to Affine.append is not affine!");
        }
        var a = this.a * v.a + this.c * v.b;
        var b = this.b * v.a + this.d * v.b;
        var c = this.a * v.c + this.c * v.d;
        var d = this.b * v.c + this.d * v.d;
        var e = this.a * v.e + this.c * v.f + this.e;
        var f = this.b * v.e + this.d * v.f + this.f;
        return new Affine( a, b, c, d, e, f );
    };

    Affine.prototype.toString = function() {
        return ("affine: "   + this.a + " " + this.c + " " + this.e +
            "\n        " + this.b + " " + this.d + " " + this.f);
    };

    // Utility functions ---------------------------------

    // Browsers return a string rather than a transform list for gradientTransform!
    function parseTransform(t) {
        // console.log( "parseTransform: " + t );
        var affine = new Affine();
        for (var i in t = t.match(/(\w+\(\s*(\-?\d+\.?\d*e?\-?\d*\s*,?\s*)+\))+/g)) {
            var c = t[i].match(/[\w\.\-]+/g);
            var type = c.shift();
            switch ( type ) {

                case "translate":
                    var trans;
                    if (c.length == 2) {
                        trans = new Affine( 1, 0, 0, 1, c[0], c[1] );
                    } else {
                        console.log( "mesh.js: translate does not have 2 arguments!" );
                        trans = new Affine( 1, 0, 0, 1, 0, 0 );
                    }
                    console.log( trans.toString() );
                    affine = affine.append( trans );
                    break;

                case "scale":
                    var scale;
                    if (c.length == 1 ) {
                        scale = new Affine( c[0], 0, 0, c[0], 0, 0 );
                    } else if (c.length == 2) {
                        scale = new Affine( c[0], 0, 0, c[1], 0, 0 );
                    } else {
                        console.log( "mesh.js: scale does not have 1 or 2 arguments!" );
                        scale = new Affine( 1, 0, 0, 1, 0, 0 );
                    }
                    affine = affine.append( scale );
                    break;

                case "rotate":
                    if (c.length == 3 ) {
                        var trans = new Affine( 1, 0, 0, 1, c[1], c[2]);
                        affine = affine.append( trans );
                    }
                    if (c[0]) {
                        var radian = c[0] * Math.PI/180.0;
                        var cos = Math.cos(radian);
                        var sin = Math.sin(radian);
                        if (Math.abs( cos ) < 1e-16) { // I hate rounding errors...
                            cos = 0;
                        }
                        if (Math.abs( sin ) < 1e-16) { // I hate rounding errors...
                            sin = 0;
                        }
                        var rotate = new Affine( cos, sin, -sin, cos, 0, 0 );
                        affine = affine.append( rotate );
                    } else {
                        console.log( "math.js: No argument to rotate transform!" );
                    }
                    if (c.length == 3 ) {
                        var trans = new Affine( 1, 0, 0, 1, -c[1], -c[2]);
                        affine = affine.append( trans );
                    }
                    break;

                case "skewX":
                    if (c[0]) {
                        var radian = c[0] * Math.PI/180.0;
                        var tan = Math.tan(radian);
                        var skewx = new Affine( 1, 0, tan, 1, 0, 0 );
                        affine = affine.append( skewx );
                    } else {
                        console.log( "math.js: No argument to skewX transform!" );
                    }
                    break;

                case "skewY":
                    if (c[0]) {
                        var radian = c[0] * Math.PI/180.0;
                        var tan = Math.tan(radian);
                        var skewy = new Affine( 1, tan, 0, 1, 0, 0 );
                        affine = affine.append( skewy );
                    } else {
                        console.log( "math.js: No argument to skewY transform!" );
                    }
                    break;

                case "matrix":
                    if (c.length == 6) {
                        var matrix = new Affine( c[0], c[1], c[2], c[3], c[4], c[5] );
                        affine = affine.append( matrix );
                    } else {
                        console.log( "math.js: Incorrect number of arguments for matrix!" );
                    }
                    break;

                default:
                    console.log( "mesh.js: Unhandled transform type: " + type);
                    break;
            }
        }
        // console.log( "  affine:\n" + affine.toString() );
        return affine;
    };


    function colorToString(c) {
        return ("rgb(" + Math.round(c[0]) + "," + Math.round(c[1]) + "," +Math.round(c[2]) + ")");
    };

    // Split Bezier using de Casteljau's method.
    function split_bezier(p0, p1, p2, p3) {

        // console.log( "split_bezier" );
        var p00 = p0.clone();
        var p13 = p3.clone();

        var tmp = p1.add(p2).scale(0.5);
        var p01 = p0.add(p1).scale(0.5);
        var p12 = p2.add(p3).scale(0.5);

        var p02 = p01.add(tmp).scale(0.5);
        var p11 = tmp.add(p12).scale(0.5);

        var p03 = p02.add(p11).scale(0.5);
        var p10 = p03.clone();

        return ([[p00, p01, p02, p03],
            [p10, p11, p12, p13]]);
    }

    // See Cairo: cairo-mesh-pattern-rasterizer.c
    function bezier_steps_sq(points) {
        var tmp = [];
        tmp[0] = points[0].dist_sq(points[1]);
        tmp[1] = points[2].dist_sq(points[3]);
        tmp[2] = points[0].dist_sq(points[2]) * 0.25;
        tmp[3] = points[1].dist_sq(points[3]) * 0.25;
        return Math.max.apply(null,tmp) * 18;
    };


    // Curve class --------------------------------------
    function Curve(nodes, colors) {
        this.nodes = nodes;  // 4 Bezier points
        this.colors = colors; // 2 x 4 colors (two ends x R+G+B+A)
    };

    // Paint a Bezier curve. w is width of Canvas window.
    Curve.prototype.paint_curve = function(v, w) {

        // console.log( "Curve.paint_curve" );
        // If inside, see if we need to split
        var max = bezier_steps_sq(this.nodes);

        if (max > 2.0) {  // Larger values leave holes, smaller take longer to render.
            var beziers = split_bezier(this.nodes[0],this.nodes[1],this.nodes[2],this.nodes[3]);
            var colors0 = [[],[]]; // ([start][end])
            var colors1 = [[],[]];
            for (var i = 0; i < 4; ++ i) {
                colors0[0][i] = this.colors[0][i];
                colors0[1][i] = (this.colors[0][i] + this.colors[1][i])/2;
                colors1[0][i] = (this.colors[0][i] + this.colors[1][i])/2;
                colors1[1][i] = this.colors[1][i];
            }
            var curve0 = new Curve( beziers[0], colors0 );
            var curve1 = new Curve( beziers[1], colors1 );
            curve0.paint_curve(v, w);
            curve1.paint_curve(v, w);
        } else {
            counter++;

            // Directly write data
            var x = Math.round(this.nodes[0].x);
            var y = Math.round(this.nodes[0].y);
            if (x >= 0 && x < w ) {
                var index = (y * w + x) * 4;
                v[index    ] = Math.round(this.colors[0][0]);
                v[index + 1] = Math.round(this.colors[0][1]);
                v[index + 2] = Math.round(this.colors[0][2]);
                v[index + 3] = Math.round(this.colors[0][3]); // Alpha
            }

            // Draw curve, quick and dirty (via canvas context)
            // v.beginPath();
            // v.moveTo(        this.nodes[0].x, this.nodes[0].y );
            // v.bezierCurveTo( this.nodes[1].x, this.nodes[1].y,
            // 		     this.nodes[2].x, this.nodes[2].y,
            // 		     this.nodes[3].x, this.nodes[3].y );
            // v.strokeStyle = colorToString( this.colors[0] );
            // v.stroke();
        }
    }

    // Patch class -------------------------------------
    function Patch(nodes, colors) {
        this.nodes = nodes;   // 4x4 array of points
        this.colors = colors; // 2x2x4 colors (four corners x R+G+B+A)
    };

    // Set path for future stroking or filling... useful for debugging.
    Patch.prototype.setOutline = function(v) {

        // Draw patch outline
        v.beginPath();
        v.moveTo(        this.nodes[0][0].x, this.nodes[0][0].y );
        v.bezierCurveTo( this.nodes[0][1].x, this.nodes[0][1].y,
            this.nodes[0][2].x, this.nodes[0][2].y,
            this.nodes[0][3].x, this.nodes[0][3].y );
        v.bezierCurveTo( this.nodes[1][3].x, this.nodes[1][3].y,
            this.nodes[2][3].x, this.nodes[2][3].y,
            this.nodes[3][3].x, this.nodes[3][3].y );
        v.bezierCurveTo( this.nodes[3][2].x, this.nodes[3][2].y,
            this.nodes[3][1].x, this.nodes[3][1].y,
            this.nodes[3][0].x, this.nodes[3][0].y );
        v.bezierCurveTo( this.nodes[2][0].x, this.nodes[2][0].y,
            this.nodes[1][0].x, this.nodes[1][0].y,
            this.nodes[0][0].x, this.nodes[0][0].y );
        v.closePath();
    };

    // Draw stroke patch... useful if debugging.
    Patch.prototype.drawOutline = function(v) {

        this.setOutline(v);
        v.strokeStyle = "black";
        v.stroke();
    };

    // Fill patch... useful if debugging.
    Patch.prototype.fillOutline = function(v) {

        this.setOutline(v);
        v.fillStyle = colorToString( this.colors[0] );
        v.fill();
    };

    // Split patch horizontally into two patches.
    Patch.prototype.split = function() {

        // console.log( "Patch.split" );

        var nodes0  = [[],[],[],[]];
        var nodes1  = [[],[],[],[]];
        var colors0 = [[[],[]],[[],[]]];
        var colors1 = [[[],[]],[[],[]]];

        for (var i = 0; i < 4; ++i) {
            var beziers = split_bezier( this.nodes[0][i], this.nodes[1][i], this.nodes[2][i], this.nodes[3][i] );
            for (var j = 0; j < 4; ++j) {
                nodes0[0][i] = beziers[0][0];
                nodes0[1][i] = beziers[0][1];
                nodes0[2][i] = beziers[0][2];
                nodes0[3][i] = beziers[0][3];
                nodes1[0][i] = beziers[1][0];
                nodes1[1][i] = beziers[1][1];
                nodes1[2][i] = beziers[1][2];
                nodes1[3][i] = beziers[1][3];
            }
        }

        for (var i = 0; i < 4; ++ i) {
            colors0[0][0][i] = this.colors[0][0][i];
            colors0[0][1][i] = this.colors[0][1][i];
            colors0[1][0][i] = (this.colors[0][0][i] + this.colors[1][0][i])/2;
            colors0[1][1][i] = (this.colors[0][1][i] + this.colors[1][1][i])/2;
            colors1[0][0][i] = (this.colors[0][0][i] + this.colors[1][0][i])/2;
            colors1[0][1][i] = (this.colors[0][1][i] + this.colors[1][1][i])/2;
            colors1[1][0][i] = this.colors[1][0][i];
            colors1[1][1][i] = this.colors[1][1][i];
        }

        var patch0 = new Patch( nodes0, colors0 );
        var patch1 = new Patch( nodes1, colors1 );

        return ([patch0, patch1]);
    };

    Patch.prototype.paint = function(v, w) {

        // console.log( "Patch.paint" );
        // console.log( this.nodes );

        // Check if patch is inside canvas (need canvas dimensions)
        // To be done.....

        // If inside, see if we need to split
        var tmp = [];
        for (var i = 0; i < 4; ++i ) {
            tmp[i] = bezier_steps_sq([this.nodes[0][i],this.nodes[1][i],
                this.nodes[2][i],this.nodes[3][i]]);
        }

        var max = Math.max.apply(null,tmp);
        // console.log( "Max: " + max );

        if (max > 2.0) {  // Larger values leave holes, smaller take longer to render.
            // console.log( "Paint: Splitting" );
            var patches = this.split();
            // console.log( patches );
            patches[0].paint(v, w);
            patches[1].paint(v, w)
        } else {
            // console.log( "Paint: Filling" );
            //this.fillOutline(v);
            this.paint_curve(v, w);
        }
    };

    Patch.prototype.paint_curve = function(v, w) {

        // console.log( "Patch.paint_curve" );

        // Paint a Bezier curve using just the top of the patch. If
        // the patch is thin enough this should work. We leave this
        // function here in case we want to do something more fancy.
        var curve = new Curve(
            [this.nodes[0][0],this.nodes[0][1],this.nodes[0][2],this.nodes[0][3]],
            [this.colors[0][0],this.colors[0][1]]);
        curve.paint_curve(v, w);
    }


    // Mesh class ---------------------------------------
    function Mesh(id) {
        // console.log( "Mesh: " + id );
        this.id = id;
        var raw = this.readMesh( id );
        this.nodes  = raw.nodes;  // (m*3+1) x (n*3+1) points
        this.colors = raw.colors; // (m+1) x (n+1) x 4  colors (R+G+B+A)
        // console.log( this.nodes );
        // console.log( this.colors );
    };

    // Weighted average to find Bezier points for linear sides.
    function w_ave(p0, p1) {
        var p = p0.scale(2.0/3.0).add(p1.scale(1.0/3.0));
        return p;
    }

    // Function to parse an SVG mesh and return an array of nodes (points) and an array of colors.
    Mesh.prototype.readMesh = function(id) {

        var nodes  = [];
        var colors = [];

        // First, find the mesh
        var theMesh = document.getElementById(id);
        if (theMesh == null) {
            console.log( "mesh.js: Could not find mesh: " + id);
        } else {
            // console.log( "Reading mesh: " + id);

            nodes[0]  = []; // Top row
            colors[0] = []; // Top row

            var x = Number(theMesh.getAttribute("x"));
            var y = Number(theMesh.getAttribute("y"));
            // console.log( " x: " + x + " y: " + y );
            nodes[0][0] = new Point(x, y);

            var rows = theMesh.children
            for (var i = 0; i < rows.length; ++i ) {
                // Need to validate if meshrow...
                nodes[ 3*i+1] = []; // Need three extra rows for each meshrow.
                nodes[ 3*i+2] = [];
                nodes[ 3*i+3] = [];
                colors[i+1] = []; // Need one more row than number of meshrows.
                // console.log( " row: " + i);
                var patches = rows[i].children;
                for (var j = 0; j < patches.length; ++j) {
                    // console.log( "  patch: " + j);
                    var stops = patches[j].children;
                    for (var k = 0; k < stops.length; ++k) {
                        var l = k;
                        if (i != 0) {
                            ++l; // There is no top if row isn't first row.
                        }
                        // console.log( "   stop: " + k);
                        var path = stops[k].getAttribute("path");

                        var type = "l"; // We need to still find mid-points even if no path.
                        if (path != null) {
                            var parts = path.match(/\s*([lLcC])\s*(.*)/);
                            type = parts[1];
                        }
                        var stop_nodes = parse_points( parts[2] );

                        switch (type) {
                            case "l":
                                if (l == 0) { // Top
                                    nodes[3*i][3*j+3] = stop_nodes[0].add(nodes[3*i][3*j]);
                                    nodes[3*i][3*j+1] = w_ave( nodes[3*i][3*j], nodes[3*i][3*j+3] );
                                    nodes[3*i][3*j+2] = w_ave( nodes[3*i][3*j+3], nodes[3*i][3*j] );
                                } else if (l == 1) { // Right
                                    nodes[3*i+3][3*j+3] = stop_nodes[0].add(nodes[3*i][3*j+3]);
                                    nodes[3*i+1][3*j+3] = w_ave( nodes[3*i][3*j+3], nodes[3*i+3][3*j+3] );
                                    nodes[3*i+2][3*j+3] = w_ave( nodes[3*i+3][3*j+3], nodes[3*i][3*j+3] );
                                } else if (l == 2) { // Bottom
                                    if(j==0) {
                                        nodes[3*i+3][3*j+0] = stop_nodes[0].add(nodes[3*i+3][3*j+3]);
                                    }
                                    nodes[3*i+3][3*j+1] = w_ave( nodes[3*i+3][3*j], nodes[3*i+3][3*j+3] );
                                    nodes[3*i+3][3*j+2] = w_ave( nodes[3*i+3][3*j+3], nodes[3*i+3][3*j] );
                                } else { // Left
                                    nodes[3*i+1][3*j] = w_ave( nodes[3*i][3*j], nodes[3*i+3][3*j] );
                                    nodes[3*i+2][3*j] = w_ave( nodes[3*i+3][3*j], nodes[3*i][3*j] );
                                }
                                break;
                            case "L":
                                if (l == 0) { // Top
                                    nodes[3*i][3*j+3] = stop_nodes[0];
                                    nodes[3*i][3*j+1] = w_ave( nodes[3*i][3*j], nodes[3*i][3*j+3] );
                                    nodes[3*i][3*j+2] = w_ave( nodes[3*i][3*j+3], nodes[3*i][3*j] );
                                } else if (l == 1) { // Right
                                    nodes[3*i+3][3*j+3] = stop_nodes[0];
                                    nodes[3*i+1][3*j+3] = w_ave( nodes[3*i][3*j+3], nodes[3*i+3][3*j+3] );
                                    nodes[3*i+2][3*j+3] = w_ave( nodes[3*i+3][3*j+3], nodes[3*i][3*j+3] );
                                } else if (l == 2) { // Bottom
                                    if(j==0) {
                                        nodes[3*i+3][3*j+0] = stop_nodes[0];
                                    }
                                    nodes[3*i+3][3*j+1] = w_ave( nodes[3*i+3][3*j], nodes[3*i+3][3*j+3] );
                                    nodes[3*i+3][3*j+2] = w_ave( nodes[3*i+3][3*j+3], nodes[3*i+3][3*j] );
                                } else { // Left
                                    nodes[3*i+1][3*j] = w_ave( nodes[3*i][3*j], nodes[3*i+3][3*j] );
                                    nodes[3*i+2][3*j] = w_ave( nodes[3*i+3][3*j], nodes[3*i][3*j] );
                                }
                                break;
                            case "c":
                                if (l == 0) { // Top
                                    nodes[3*i][3*j+1] = stop_nodes[0].add(nodes[3*i][3*j]);
                                    nodes[3*i][3*j+2] = stop_nodes[1].add(nodes[3*i][3*j]);
                                    nodes[3*i][3*j+3] = stop_nodes[2].add(nodes[3*i][3*j]);
                                } else if (l == 1) { // Right
                                    nodes[3*i+1][3*j+3] = stop_nodes[0].add(nodes[3*i][3*j+3]);
                                    nodes[3*i+2][3*j+3] = stop_nodes[1].add(nodes[3*i][3*j+3]);
                                    nodes[3*i+3][3*j+3] = stop_nodes[2].add(nodes[3*i][3*j+3]);
                                } else if (l == 2) { // Bottom
                                    nodes[3*i+3][3*j+2] = stop_nodes[0].add(nodes[3*i+3][3*j+3]);
                                    nodes[3*i+3][3*j+1] = stop_nodes[1].add(nodes[3*i+3][3*j+3]);
                                    if(j==0) {
                                        nodes[3*i+3][3*j+0] = stop_nodes[2].add(nodes[3*i+3][3*j+3]);
                                    }
                                } else { // Left
                                    nodes[3*i+2][3*j] = stop_nodes[0].add(nodes[3*i+3][3*j]);
                                    nodes[3*i+1][3*j] = stop_nodes[1].add(nodes[3*i+3][3*j]);
                                }
                                break;
                            case "C":
                                if (l == 0) { // Top
                                    nodes[3*i][3*j+1] = stop_nodes[0];
                                    nodes[3*i][3*j+2] = stop_nodes[1];
                                    nodes[3*i][3*j+3] = stop_nodes[2];
                                } else if (l == 1) { // Right
                                    nodes[3*i+1][3*j+3] = stop_nodes[0];
                                    nodes[3*i+2][3*j+3] = stop_nodes[1];
                                    nodes[3*i+3][3*j+3] = stop_nodes[2];
                                } else if (l == 2) { // Bottom
                                    nodes[3*i+3][3*j+2] = stop_nodes[0];
                                    nodes[3*i+3][3*j+1] = stop_nodes[1];
                                    if(j==0) {
                                        nodes[3*i+3][3*j+0] = stop_nodes[2];
                                    }
                                } else { // Left
                                    nodes[3*i+2][3*j] = stop_nodes[0];
                                    nodes[3*i+1][3*j] = stop_nodes[1];
                                }
                                break
                            default:
                                console.log("mesh.js: " + type + " invalid path type.");
                        }

                        if ( (i == 0 && j == 0) || k > 0 ) {
                            var color_raw = getComputedStyle(stops[k]).stopColor.match(/^rgb\s*\(\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*\)$/i);
                            var alpha_raw = getComputedStyle(stops[k]).stopOpacity;
                            // console.log( "   color_raw: " + color_raw + " alpha_raw: " + alpha_raw);
                            var alpha = 255;
                            if (alpha_raw) {
                                alpha = parseInt(alpha_raw * 255);
                            }
                            // console.log( "   alpha: " + alpha );
                            if (color_raw) {
                                if (l == 0) { // upper left corner
                                    colors[i][j] = [];
                                    colors[i][j][0] = parseInt(color_raw[1]);
                                    colors[i][j][1] = parseInt(color_raw[2]);
                                    colors[i][j][2] = parseInt(color_raw[3]);
                                    colors[i][j][3] = alpha; // Alpha
                                } else if (l == 1) { // upper right corner
                                    colors[i][j+1] = [];
                                    colors[i][j+1][0] = parseInt(color_raw[1]);
                                    colors[i][j+1][1] = parseInt(color_raw[2]);
                                    colors[i][j+1][2] = parseInt(color_raw[3]);
                                    colors[i][j+1][3] = alpha; // Alpha
                                } else if (l == 2) { // lower right corner
                                    colors[i+1][j+1] = [];
                                    colors[i+1][j+1][0] = parseInt(color_raw[1]);
                                    colors[i+1][j+1][1] = parseInt(color_raw[2]);
                                    colors[i+1][j+1][2] = parseInt(color_raw[3]);
                                    colors[i+1][j+1][3] = alpha; // Alpha
                                } else if (l == 3) { // lower left corner
                                    colors[i+1][j] = [];
                                    colors[i+1][j][0] = parseInt(color_raw[1]);
                                    colors[i+1][j][1] = parseInt(color_raw[2]);
                                    colors[i+1][j][2] = parseInt(color_raw[3]);
                                    colors[i+1][j][3] = alpha; // Alpha
                                }
                            }
                        }
                    }

                    // SVG doesn't use tensor points but we need them for rendering.
                    nodes[3*i+1][3*j+1] = new Point;
                    nodes[3*i+1][3*j+2] = new Point;
                    nodes[3*i+2][3*j+1] = new Point;
                    nodes[3*i+2][3*j+2] = new Point;

                    nodes[3*i+1][3*j+1].x =
                        ( -4.0 *   nodes[3*i  ][3*j  ].x +
                            6.0 * ( nodes[3*i  ][3*j+1].x + nodes[3*i+1][3*j  ].x ) +
                            -2.0 * ( nodes[3*i  ][3*j+3].x + nodes[3*i+3][3*j  ].x ) +
                            3.0 * ( nodes[3*i+3][3*j+1].x + nodes[3*i+1][3*j+3].x ) +
                            -1.0 *   nodes[3*i+3][3*j+3].x ) / 9.0;
                    nodes[3*i+1][3*j+2].x =
                        ( -4.0 *   nodes[3*i  ][3*j+3].x +
                            6.0 * ( nodes[3*i  ][3*j+2].x + nodes[3*i+1][3*j+3].x ) +
                            -2.0 * ( nodes[3*i  ][3*j  ].x + nodes[3*i+3][3*j+3].x ) +
                            3.0 * ( nodes[3*i+3][3*j+2].x + nodes[3*i+1][3*j  ].x ) +
                            -1.0 *   nodes[3*i+3][3*j  ].x ) / 9.0;
                    nodes[3*i+2][3*j+1].x =
                        ( -4.0 *   nodes[3*i+3][3*j  ].x +
                            6.0 * ( nodes[3*i+3][3*j+1].x + nodes[3*i+2][3*j  ].x ) +
                            -2.0 * ( nodes[3*i+3][3*j+3].x + nodes[3*i  ][3*j  ].x ) +
                            3.0 * ( nodes[3*i  ][3*j+1].x + nodes[3*i+2][3*j+3].x ) +
                            -1.0 *   nodes[3*i  ][3*j+3].x ) / 9.0;
                    nodes[3*i+2][3*j+2].x =
                        ( -4.0 *   nodes[3*i+3][3*j+3].x +
                            6.0 * ( nodes[3*i+3][3*j+2].x + nodes[3*i+2][3*j+3].x ) +
                            -2.0 * ( nodes[3*i+3][3*j  ].x + nodes[3*i  ][3*j+3].x ) +
                            3.0 * ( nodes[3*i  ][3*j+2].x + nodes[3*i+2][3*j  ].x ) +
                            -1.0 *   nodes[3*i  ][3*j  ].x ) / 9.0;

                    nodes[3*i+1][3*j+1].y =
                        ( -4.0 *   nodes[3*i  ][3*j  ].y +
                            6.0 * ( nodes[3*i  ][3*j+1].y + nodes[3*i+1][3*j  ].y ) +
                            -2.0 * ( nodes[3*i  ][3*j+3].y + nodes[3*i+3][3*j  ].y ) +
                            3.0 * ( nodes[3*i+3][3*j+1].y + nodes[3*i+1][3*j+3].y ) +
                            -1.0 *   nodes[3*i+3][3*j+3].y ) / 9.0;
                    nodes[3*i+1][3*j+2].y =
                        ( -4.0 *   nodes[3*i  ][3*j+3].y +
                            6.0 * ( nodes[3*i  ][3*j+2].y + nodes[3*i+1][3*j+3].y ) +
                            -2.0 * ( nodes[3*i  ][3*j  ].y + nodes[3*i+3][3*j+3].y ) +
                            3.0 * ( nodes[3*i+3][3*j+2].y + nodes[3*i+1][3*j  ].y ) +
                            -1.0 *   nodes[3*i+3][3*j  ].y ) / 9.0;
                    nodes[3*i+2][3*j+1].y =
                        ( -4.0 *   nodes[3*i+3][3*j  ].y +
                            6.0 * ( nodes[3*i+3][3*j+1].y + nodes[3*i+2][3*j  ].y ) +
                            -2.0 * ( nodes[3*i+3][3*j+3].y + nodes[3*i  ][3*j  ].y ) +
                            3.0 * ( nodes[3*i  ][3*j+1].y + nodes[3*i+2][3*j+3].y ) +
                            -1.0 *   nodes[3*i  ][3*j+3].y ) / 9.0;
                    nodes[3*i+2][3*j+2].y =
                        ( -4.0 *   nodes[3*i+3][3*j+3].y +
                            6.0 * ( nodes[3*i+3][3*j+2].y + nodes[3*i+2][3*j+3].y ) +
                            -2.0 * ( nodes[3*i+3][3*j  ].y + nodes[3*i  ][3*j+3].y ) +
                            3.0 * ( nodes[3*i  ][3*j+2].y + nodes[3*i+2][3*j  ].y ) +
                            -1.0 *   nodes[3*i  ][3*j  ].y ) / 9.0;

                }
            }
            // console.log( nodes );
        }
        return {
            nodes: nodes,
            colors: colors
        };
    }

    // Extracts out each patch and then paints it
    Mesh.prototype.paint = function(v, w) {

        for (var i = 0; i < (this.nodes.length-1)/3; ++i) {
            for (var j = 0; j < (this.nodes[0].length-1)/3; ++j) {

                var slice_nodes = [];
                for ( var k = i*3; k < (i*3)+4; ++k ) {
                    slice_nodes.push(this.nodes[k].slice(j*3,(j*3)+4));
                }

                var slice_colors = [];
                slice_colors.push(this.colors[i  ].slice(j,j+2));
                slice_colors.push(this.colors[i+1].slice(j,j+2));

                var patch = new Patch(slice_nodes, slice_colors);
                patch.paint(v, w);
            }
        }
    };

    // Transforms mesh into coordinate space of canvas (t is either Point or Affine).
    Mesh.prototype.transform = function(t) {
        // console.log( "t: " + t );
        if (t instanceof Point) {
            for (var i = 0; i < this.nodes.length; ++i) {
                for (var j = 0; j < this.nodes[0].length; ++j) {
                    this.nodes[i][j] = this.nodes[i][j].add(t);
                }
            }
        }
        if (t instanceof Affine) {
            for (var i = 0; i < this.nodes.length; ++i) {
                for (var j = 0; j < this.nodes[0].length; ++j) {
                    this.nodes[i][j] = this.nodes[i][j].transform(t);
                }
            }
        }
    };

    // Scale mesh into coordinate space of canvas (t is a Point).
    Mesh.prototype.scale = function(t) {
        for (var i = 0; i < this.nodes.length; ++i) {
            for (var j = 0; j < this.nodes[0].length; ++j) {
                this.nodes[i][j] = this.nodes[i][j].scale(t);
            }
        }
    };

    function parse_points(s) {

        var points = [];
        var values = s.split(/[ ,]+/);
        for (var i = 0; i < values.length-1; i += 2) {
            points.push( new Point( parseFloat( values[i]), parseFloat( values[i+1] )));
        }
        return points;
    }

    // Start of document processing ---------------------

    var shapes = document.querySelectorAll('rect,circle,ellipse,path,text');
    // console.log("Shapes: " + shapes.length);

    for (var i = 0; i < shapes.length; ++i) {
        var shape = shapes[i];
        // console.log( shape.nodeName );
        // Get id. If no id, create one.
        var shape_id = shape.getAttribute("id");
        if (!shape_id) {
            shape_id = "patchjs_shape" + i;
            shape.setAttribute("id", shape_id);
        }
        // console.log( "id: " + shape_id );

        var fill = shape.style.fill;
        var url_value = fill.match(/^url\(\s*\"?\s*#([^\s\"]+)\"?\s*\)/);
        if (url_value && url_value[1]) {
            // console.log( "Got url! " + url_value[1]);
            var mesh = document.getElementById(url_value[1]);
            // console.log( mesh );
            // console.log( mesh.nodeName );
            if (mesh.nodeName === "meshgradient" ) {
                // console.log( "Got mesh" );
                var bbox = shape.getBBox();
                // console.log( bbox );

                // Create temporary canvas
                var my_canvas = document.createElementNS( xhtmlNS, "canvas" );
                //var my_canvas = document.createElement( "canvas" );  // Both work for HTML...
                my_canvas.width = bbox.width;
                my_canvas.height = bbox.height;

                // console.log ( "Canvas: " + my_canvas.width + "x" + my_canvas.height );
                var my_context = my_canvas.getContext("2d");

                var my_canvas_image = my_context.getImageData( 0, 0, my_canvas.width, my_canvas.height);
                var my_data = my_canvas_image.data;

                // Draw a mesh
                var my_mesh = new Mesh( url_value[1] );

                // Adjust for bounding box if necessary.
                if (mesh.getAttribute( "gradientUnits" ) === "objectBoundingBox") {
                    my_mesh.scale( new Point( bbox.width, bbox.height ) );
                }

                // Apply gradient transform.
                var gradientTransform = mesh.getAttribute("gradientTransform");
                // console.log( typeof gradientTransform );
                if ( gradientTransform != null ) {
                    var affine = parseTransform( gradientTransform );
                    my_mesh.transform( affine );
                }

                // Position to Canvas coordinate.
                var t = new Point( -bbox.x, -bbox.y );
                if (mesh.getAttribute( "gradientUnits" ) === "userSpaceOnUse") {
                    my_mesh.transform(t);
                }

                // Paint
                my_mesh.paint(my_data, my_canvas.width);

                my_context.putImageData(my_canvas_image, 0, 0);

                // Create image element of correct size
                var my_image = document.createElementNS( svgNS, "image" );
                my_image.setAttribute("width", my_canvas.width);
                my_image.setAttribute("height",my_canvas.height);
                my_image.setAttribute("x", bbox.x);
                my_image.setAttribute("y", bbox.y);

                // Set image to data url
                var my_png = my_canvas.toDataURL();
                my_image.setAttributeNS(xlinkNS, "xlink:href", my_png);

                // Insert image into document
                shape.parentNode.insertBefore( my_image, shape );
                shape.style.fill = "none";

                // Create clip referencing shape and insert into document
                var clip = document.createElementNS( svgNS, "clipPath");
                var clip_id = "patchjs_clip" + i;
                clip.setAttribute("id", clip_id);
                var use = document.createElementNS( svgNS, "use");
                use.setAttributeNS( xlinkNS, "xlink:href", "#" + shape_id);
                clip.appendChild(use);
                shape.parentElement.insertBefore(clip, shape);
                my_image.setAttribute("clip-path", "url(#" + clip_id + ")");
            }
        }
    }
})();