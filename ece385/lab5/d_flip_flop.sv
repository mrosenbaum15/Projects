module d_flip_flop
(
        input logic Clk,
        input logic Load,
        input logic Reset,
        input logic D,
        output logic Q
);

always_ff @ (posedge Clk or posedge Reset) begin

        Q <= Q

        if(Load)
            Q <= D;
        else if(Reset)
            Q <= 1'b0;
end

endmodule
