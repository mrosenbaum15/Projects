module round_counter(
        input logic CLK,
        input logic RESET,
        input logic add_bool,
        output logic [4:0] count_out
);

logic [3:0] count_holder;

always_ff @ (posedge CLK) begin

    if(RESET)
        count_out <= 4'd0;
    else if(add_bool)
        count_out <= count_holder;

end

always_comb begin

    if(count_out < 4'd10)
        count_holder = count_out + 4'd1;
    else
        count_holder = 4'd0;

end

endmodule
