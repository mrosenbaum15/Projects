// creating simple 2:1 MUX
// inputs: in0, in1 (data-in, 2 bits), select (1 bit)
// output: mux_output (output result)
module two_one_MUX
(
			input logic [15:0] in0,
			input logic [15:0] in1,
			input logic select,
			output logic [15:0] mux_output
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

// creating simple 4:1 MUX
// inputs: in0, in1, in2, in3 (data-in, 2 bits each), select (2 bit)
// output: mux_output (2-bit output result)
module four_one_MUX
(
			input logic [15:0] in0,
			input logic [15:0] in1,
            input logic [15:0] in2,
            input logic [15:0] in3,
			input logic [1:0] select,
			output logic [15:0] mux_output
);


always_comb

	begin

		// determing which data bit should be selected
		if(select == 2'b00)
			mux_output = in0;
		else if(select == 2'b01)
			mux_output = in1;
        else if(select == 2'b10)
    		mux_output = in2;
        else if(select == 2'b11)
        	mux_output = in3;

	end

endmodule
