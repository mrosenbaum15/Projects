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
	  
	  logic C4, C8, C12;
	  
	  four_bit_ripple final_select_0(.x(A[3:0]), .y(B[3:0]), .cin(1'b0), .sum(Sum[3:0]), .cout(C4));
	  select_adder final_select_1(.x(A[7:4]), .y(B[7:4]), .cin(C4), .sum(Sum[7:4]), .cout(C8));
	  select_adder final_select_2(.x(A[11:8]), .y(B[11:8]), .cin(C8), .sum(Sum[11:8]), .cout(C12));
	  select_adder final_select_3(.x(A[15:12]), .y(B[15:12]), .cin(C12), .sum(Sum[15:12]), .cout(CO));
		     
endmodule

module select_adder
(
		input[3:0] x,
		input[3:0] y,
		input cin,
		output logic [3:0] sum,
		output logic cout
);

logic c0, c1;
logic [3:0] sum_first;
logic [3:0] sum_second;

four_bit_ripple first_select_0(.x(x), .y(y), .cin(0), .sum(sum_first), .cout(c0));
four_bit_ripple first_select_1(.x(x), .y(y), .cin(1), .sum(sum_second), .cout(c1));

two_one_MUX mux_0(.in0(sum_first[0]), .in1(sum_second[0]), .select(cin), .mux_output(sum[0]));
two_one_MUX mux_1(.in0(sum_first[1]), .in1(sum_second[1]), .select(cin), .mux_output(sum[1]));
two_one_MUX mux_2(.in0(sum_first[2]), .in1(sum_second[2]), .select(cin), .mux_output(sum[2]));
two_one_MUX mux_3(.in0(sum_first[3]), .in1(sum_second[3]), .select(cin), .mux_output(sum[3]));
two_one_MUX mux_4(.in0(c0), .in1(c1), .select(cin), .mux_output(cout));

endmodule

module two_one_MUX
(
			input logic in0,
			input logic in1,
			input logic select,
			output logic mux_output
);
			
			
always_comb

	begin
	
		if(select == 1'b0)
			mux_output = in0;
		else
			mux_output = in1;
			
	end
	
endmodule

