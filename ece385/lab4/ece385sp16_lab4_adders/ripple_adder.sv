module ripple_adder
(
    input   logic[15:0]     A,
    input   logic[15:0]     B,
    output  logic[15:0]     Sum,
    output  logic           CO
);

    /* TODO
     *
     * Insert code here to implement a ripple adder.
     * Your code should be completly combinational (don't use always_ff or always_latch).
     * Feel free to create sub-modules or other files. */
	  
	  // carry bits between the adders
	  logic C0, C1, C2;
	  
	  // creating four individual adders together to make it a 16-bit ripple adder
	  four_bit_ripple FBR_0(.x(A[3:0]), .y(B[3:0]), .cin(0), .sum(Sum[3:0]), .cout(C0));
	  four_bit_ripple FBR_1(.x(A[7:4]), .y(B[7:4]), .cin(C0), .sum(Sum[7:4]), .cout(C1));
	  four_bit_ripple FBR_2(.x(A[11:8]), .y(B[11:8]), .cin(C1), .sum(Sum[11:8]), .cout(C2));
	  four_bit_ripple FBR_3(.x(A[15:12]), .y(B[15:12]), .cin(C2), .sum(Sum[15:12]), .cout(CO));
     
endmodule

// creating 4-bit ripple adder
// inputs: x, y (4 bits each), cin (carry-in)
// outputs: sum (4 bits), cout (carry-out)
module four_bit_ripple
(
			input [3:0] x,
			input [3:0]	y,
			input cin,
			output logic [3:0] sum,
			output logic cout
);	

// carry bits between adders
logic c0, c1, c2;

// each ripple adder has four 1-bit full adders
full_adder full_one(.x(x[0]), .y(y[0]), .cin(cin), .sum(sum[0]), .cout(c0));
full_adder full_two(.x(x[1]), .y(y[1]), .cin(c0), .sum(sum[1]), .cout(c1));
full_adder full_three(.x(x[2]), .y(y[2]), .cin(c1), .sum(sum[2]), .cout(c2));
full_adder full_four(.x(x[3]), .y(y[3]), .cin(c2), .sum(sum[3]), .cout(cout));

endmodule

// based off given code in lab 4 manual
module full_adder
(
			input x,
			input y,
			input cin,
			output logic sum,
			output logic cout
);


assign sum = x ^ y ^ cin;
assign cout = (x&y) | (y&cin) | (x&cin);

endmodule
