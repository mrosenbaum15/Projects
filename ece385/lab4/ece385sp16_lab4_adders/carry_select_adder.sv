module carry_select_adder
(
    input   logic[15:0]     A,
    input   logic[15:0]     B,
    output  logic[15:0]     Sum,
    output  logic           CO
);

    /* TODO
     *
     * Insert code here to implement a carry select.
     * Your code should be completly combinational (don't use always_ff or always_latch).
     * Feel free to create sub-modules or other files. */
	  
	  // carry bits after each select adder (incremented by 4)
	  logic C4, C8, C12;
	  
	  // first adder is just a 4-bit ripple adder...based off diagram on page 6 of lab4 manual
	  four_bit_ripple final_select_0(.x(A[3:0]), .y(B[3:0]), .cin(1'b0), .sum(Sum[3:0]), .cout(C4));
	
	  // need three 4-bit select adders...based off diagram on page 6 on lab4 manual
	  select_adder final_select_1(.x(A[7:4]), .y(B[7:4]), .cin(C4), .sum(Sum[7:4]), .cout(C8));
	  select_adder final_select_2(.x(A[11:8]), .y(B[11:8]), .cin(C8), .sum(Sum[11:8]), .cout(C12));
	  select_adder final_select_3(.x(A[15:12]), .y(B[15:12]), .cin(C12), .sum(Sum[15:12]), .cout(CO));
		     
endmodule

// creating select adder
// inputs: x, y (4 bits), cin (carry-in)
// outputs: sum (4 bits), cout (carry-out)
module select_adder
(
		input[3:0] x,
		input[3:0] y,
		input cin,
		output logic [3:0] sum,
		output logic cout
);

	logic [1:0] C; // two carry bits... one between each adder and one after second adder
	logic [3:0] sum_first; // sum of first addder
	logic [3:0] sum_second; // sum off adder
	
	// need two adders, seen within diagram on page 6 of lab4 manual
	four_bit_ripple first_select_0(.x(x), .y(y), .cin(0), .sum(sum_first), .cout(C[0]));
	four_bit_ripple first_select_1(.x(x), .y(y), .cin(1), .sum(sum_second), .cout(C[1]));

	// need four MUXs as seen in diagram on page 6 of lab4 manual... one for each of the 4 sum bits
two_one_MUX mux_0(.in0(sum_first[0]), .in1(sum_second[0]), .select(cin), .mux_output(sum[0]));
two_one_MUX mux_1(.in0(sum_first[1]), .in1(sum_second[1]), .select(cin), .mux_output(sum[1]));
two_one_MUX mux_2(.in0(sum_first[2]), .in1(sum_second[2]), .select(cin), .mux_output(sum[2]));
two_one_MUX mux_3(.in0(sum_first[3]), .in1(sum_second[3]), .select(cin), .mux_output(sum[3]));
	
	// last MUX replaces AND/OR gates after each select adder
	two_one_MUX mux_4(.in0(C[0]), .in1(C[1]), .select(cin), .mux_output(cout));

endmodule

// creating simple 2:1 MUX
// inputs: in0, in1 (data-in, 2 bits), select (1 bit)
// output: mux_output (output result)
module two_one_MUX
(
			input logic in0,
			input logic in1,
			input logic select,
			output logic mux_output
);
			
			
always_comb

	begin
		
		// determing which data bit should be selected
		if(select == 1'b0)
			mux_output = in0;
		else
			mux_output = in1;
			
	end
	
endmodule

