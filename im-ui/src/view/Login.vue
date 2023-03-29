<template>
		<el-row type="flex" justify="center" class="login-container">
			<el-col :span="6">
				<el-card class="login-card" shadow="always">
					<div slot="header" class="login-brand" style="font-style: italic;font-size: 30px; margin: 5px;">欢迎登录</div>
					<el-form :model="loginForm"  status-icon :rules="rules" ref="loginForm"  label-width="60px" class="web-ruleForm" @keyup.enter.native="submitForm('loginForm')">
						<el-form-item prop="username" class="form-item">
							<span style="font-size: larger;">用户名：</span>
							<el-input type="username" v-model="loginForm.username" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item prop="password" class="form-item">
							<span style="font-size: larger;">密码：</span>
							<el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item class="form-button">
							<el-button type="primary" @click="submitForm('loginForm')">登陆</el-button>
							<el-button @click="resetForm('loginForm')">清空</el-button>
						</el-form-item >
						<div class="register">
							<router-link to="/register">没有账号,前往注册</router-link>
						</div>
					</el-form>
				</el-card>
			</el-col>
		</el-row>
</template>

<script>
	export default {
		name: "login",
		data() {
			var checkUsername = (rule, value, callback) => {
				console.log("checkUsername");
				if (!value) {
					return callback(new Error('请输入用户名'));
				}
				callback();
			};
			var checkPassword = (rule, value, callback) => {
				console.log("checkPassword");
				if (value === '') {
					callback(new Error('请输入密码'));
				}
				callback();

			};
			return {
				loginForm: {
					username: '',
					password: ''
				},
				rules: {
					username: [{
						validator: checkUsername,
						trigger: 'blur'
					}],
					password: [{
						validator: checkPassword,
						trigger: 'blur'
					}]
				}
			};
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http({
								url: "/login",
								method: 'post',
								params: this.loginForm
							})
							.then((data) => {
								this.setCookie('username',this.loginForm.username);
								this.setCookie('password',this.loginForm.password);
								this.$message.success("登陆成功");
								this.$router.push("/home/chat");
							})

					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			// 获取cookie、
			getCookie(name) {
				let reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				let arr = document.cookie.match(reg)
			    if (arr){
					 return unescape(arr[2]);
				}
			    return '';
			 },
			  // 设置cookie,增加到vue实例方便全局调用
			 setCookie (name, value, expiredays) {
			    var exdate = new Date();
			    exdate.setDate(exdate.getDate() + expiredays);
			    document.cookie = name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
			  },
			  // 删除cookie
			  delCookie (name) {
			    var exp = new Date();
			    exp.setTime(exp.getTime() - 1);
			    var cval = this.getCookie(name);
			    if (cval != null){
					document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
				}
			  }
		},
		mounted() {
			this.loginForm.username = this.getCookie("username");
			// cookie存密码并不安全，暂时是为了方便
			this.loginForm.password = this.getCookie("password");
		}
	}
</script>

<style scoped lang="scss">
.login-container{
	background:  linear-gradient(#c1fcf5, #124d4d); 
	background-size: cover;
	height: 100%;
	.login-card {
		margin-top: 6vw;
		border-radius: 0px 70px;
		.web-ruleForm {
			height: 400px;
			padding: 5px;
			overflow: hidden;
			.form-item{
				margin-right: 40px;
				margin-top: 30px;
			}
			.form-button{
				margin-left: 180px;
				margin-top: 50px;
			}
			.register{
				margin-left: 250px;
				margin-top: 40px;
			}
		}
	}
}
</style>
