module counter(
        input logic CLK,
        input logic RESET,
        input logic add_bool,
        input logic [1:0] counter_type,
        output logic [4:0] count_out
);

logic [3:0] count_holder;

logic [4:0] count_total;

always_ff @ (posedge CLK) begin

    if(RESET)
        count_out <= 5'd0;
    else if(add_bool)
        count_out <= count_holder;

end

always_comb begin

    if(counter_type == 2'd0)
        count_total = 5'd10;
    else if(counter_type == 2'd1);
        count_total = 5'd24;
    else
        count_total = 5'd4;

    if(count_out < count_total)
        count_holder = count_out + 5'd1;
    else
        count_holder = 5'd0;

end

endmodule
