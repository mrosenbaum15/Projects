module counter_key (
        input logic CLK,
        input logic RESET,
        input logic add_bool,
        output logic [4:0] count_out
);

//logic [4:0] count_holder;
//
//always_ff @ (posedge CLK) begin
//
//    if(RESET)
//        count_out <= 5'd0;
//    else if(add_bool)
//        count_out <= count_holder;
//
//end
//
//always_comb begin
//
//    if(count_out == 5'b11000)
//		  count_holder = 5'd0;
//    else
//	     count_holder = count_out + 5'd1;
//
//end

logic [4:0] temp;

		always_ff @ (posedge CLK)
		begin
			if(add_bool)
				count_out<=temp;
			else if(RESET)
				count_out<= 5'b00000;
		end

		always_comb
		begin
		if(count_out==5'b11000)
			temp = 5'b00000;
		else
			temp = count_out+5'b00001;
		end

endmodule

module counter(
        input logic CLK,
        input logic RESET,
        input logic add_bool,
        input logic [3:0] count_total,
        output logic [3:0] count_out
);

logic [3:0] count_holder;

always_ff @ (posedge CLK) begin

    if(RESET)
        count_out <= 4'd0;
    else if(add_bool)
        count_out <= count_holder;

end

always_comb begin


    if(count_out == count_total)
		  count_holder = 4'd0;
    else
	     count_holder = count_out + 4'd1;


end

endmodule
