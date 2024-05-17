/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        "primaria": '#0D0D0D',
        "secundaria": '#C24E3D',
        "terciaria": '#EDEDED',
        "hover-preto": '#292929',
        "bg-formulario" : '#808080'
      },
      fontFamily: {
        "poppins": ['Poppins','sans-serif'],
        "rubik": ['Rubik', 'sans-serif']
      }
    },
  },
  plugins: [],
}